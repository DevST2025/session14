package ra.academy.serviceimp;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UploadService {
    // bucketname
    private final String  bucketName = "upload-firebase-d8d02.appspot.com";

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private Storage storage;

    public List<String> uploadFileToServer(List<MultipartFile> list) {
        // Taọ đường dẫn đến thư mục uploads
        String uploadPath = servletContext.getRealPath("/uploads");
        // Kiểm tra thư mục có tồn tại không
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs(); // Tạo thư mục mới
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        // Upload lên server
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile f: list) {
            String fileName = dtf.format(LocalDateTime.now()) + f.getOriginalFilename();
            try {
                FileCopyUtils.copy(f.getBytes(), new File(uploadPath + File.separator + fileName));
                imageUrls.add(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return  imageUrls.stream()
                .map(this::uploadFileFromServerToFirebase)
                .collect(Collectors.toList());
    }

    // Upload file lên firebase
    public String uploadFileFromServerToFirebase(String filePath) {
        Path localPath = Paths.get(filePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(bucketName, fileName);

        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink(); // trả về đường dẫn ảnh online
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
