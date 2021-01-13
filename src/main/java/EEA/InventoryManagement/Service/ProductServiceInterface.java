package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.Entity.Product;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAllProducts();

    Product saveProduct(ProductReg productReg);

    Product save(Product savedProduct);

    Product getByID(int id);

    void deleteProduct(int id);

    void saveProductToSupplier(ProductReg productReg);

    Product getProductByID(int id);

    List<Product> getMyProducts(int supplierID);

//    Product getProductByID(int userID);
}
