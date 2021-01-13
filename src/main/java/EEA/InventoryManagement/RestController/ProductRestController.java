package EEA.InventoryManagement.RestController;

import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.Entity.Employee;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
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


}
