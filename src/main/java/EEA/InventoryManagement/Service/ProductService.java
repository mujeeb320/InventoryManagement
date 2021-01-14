package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierService supplierService;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(ProductReg productReg) {

        Product product = new Product();
        product.setId(productReg.getId());
        product.setName(productReg.getName());
        product.setPrice(productReg.getPrice());
        product.setQuantity(productReg.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public Product save(Product savedProduct) {
        return productRepository.save(savedProduct);
    }

    @Override
    public Product getByID(int id) {
        return productRepository.getOne(id);
    }
    @Override
    public Product findId(int id) {
        return productRepository.findByUserId(id);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public Product getProductByID(int userID) {
//        return productRepository.getByUserID(userID);
//    }

    @Override
    public void saveProductToSupplier(ProductReg productReg) {

        Supplier supplier = supplierService.getSupplierByID(productReg.getSupplierID());

        Product product = new Product();
        product.setName(productReg.getName());
        product.setPrice(productReg.getPrice());
        product.setQuantity(productReg.getQuantity());

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        supplier.setProductList(productList);
        product.setSupplier(supplier);
        productRepository.save(product);


    }

    @Override
    public Product getProductByID(int id) {
        return productRepository.getByID(id);
    }


    @Override
    public List<Product> getMyProducts(int supplierID) {
        return productRepository.getMyProducts(supplierID);
    }
}
