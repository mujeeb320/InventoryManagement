package EEA.InventoryManagement.RestController;

import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.Entity.Employee;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Repository.ProductRepository;
import EEA.InventoryManagement.Service.ProductService;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/allproducts")
    public List<Product> viewAllProducts()
    {
        return productService.getAllProducts();
    }


    @GetMapping("/viewProductDetails/{id}")
    public Product ViewProductUser(@PathVariable(value = "id") int id)
    {
        Product product = productService.findId(id);
        return product;
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product)
    {
        productService.save(product);
        Product newProduct = productService.getByID(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        productService.save(newProduct);
        String message = "Product Updated";
        return message;
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id) {

        Product product = productService.getByID(id);
        productService.deleteProduct(product.getId());
        return "Product Deleted";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestBody ProductReg productReg) {

        Product newProduct = productService.saveProduct(productReg);
        Product product = new Product();
        newProduct.setId(newProduct.getId());
        product.setPrice(productReg.getPrice());
        product.setQuantity(productReg.getQuantity());
        product.setName(productReg.getName());

        return "Product Added Successfully! ";
    }


}
