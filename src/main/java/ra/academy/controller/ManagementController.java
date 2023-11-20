package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.model.Product;
import ra.academy.serviceimp.ProductService;

import java.util.List;

@Controller
@RequestMapping("/administration")
public class ManagementController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String administration() {
        return "administration";
    }

    @GetMapping("/product-manager")
    public String productManager(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "product-manager";
    }

    @GetMapping("/form-add")
    public String getForm() {
        return "form-add";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "product-manager";
    }

    @GetMapping("/user-manager")
    public String userManager() {
        return "user-manager";
    }

}
