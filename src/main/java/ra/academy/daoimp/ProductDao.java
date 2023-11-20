package ra.academy.daoimp;

import org.springframework.stereotype.Component;
import ra.academy.dao.IProductDao;
import ra.academy.model.Product;
import ra.academy.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao implements IProductDao {
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        try {
            CallableStatement callSt = connection.prepareCall("{call proc_find_all_product()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setImgUrl(rs.getString("img_url"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Connection connection = ConnectDB.getConnection();
        Product product = null;
        try {
            CallableStatement callSt = connection.prepareCall("{call proc_product_find_by_Id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setImgUrl(rs.getString("img_url"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return product;
    }

    @Override
    public boolean save(Product product) {
        Product checkExists = findById(product.getId());
        Connection connection = ConnectDB.getConnection();
        if (checkExists == null) {
            // Add new product
            try {
                CallableStatement callSt = connection.prepareCall("{call proc_insert_product(?, ?, ?, ?)}");
                callSt.setString(1, product.getImgUrl());
                callSt.setString(2, product.getName());
                callSt.setDouble(3, product.getPrice());
                callSt.setInt(4, product.getStock());

                callSt.executeUpdate();
            } catch (SQLException e) {
                return false;
            } finally {
                ConnectDB.closeConnection(connection);
            }
        } else {
            // Update product
            try {
                CallableStatement callSt = connection.prepareCall("{call proc_update_product(?, ?, ?, ?, ?, ?)}");
                callSt.setInt(1, product.getId());
                callSt.setString(2, product.getImgUrl());
                callSt.setString(3, product.getName());
                callSt.setDouble(4, product.getPrice());
                callSt.setInt(5, product.getStock());
                callSt.setBoolean(6, product.isStatus());

                callSt.executeUpdate();
            } catch (SQLException e) {
                return false;
            } finally {
                ConnectDB.closeConnection(connection);
            }
        }
        return true;
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = ConnectDB.getConnection();
        try {
            CallableStatement callSt = connection.prepareCall("{call proc_delete_product(?)}");
            callSt.setInt(1, id);

            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }
}
