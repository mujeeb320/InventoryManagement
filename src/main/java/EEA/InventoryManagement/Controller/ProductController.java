package EEA.InventoryManagement.Controller;


import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Repository.ProductRepository;
import EEA.InventoryManagement.Service.ProductService;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    SupplierService supplierService;

    @GetMapping("/loadProductForm")
    public String loadProductForm(Model model) {

        model.addAttribute("products", new Product());
        return "addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("products") ProductReg productReg) {

        Product newProduct = productService.saveProduct(productReg);
        Product product = new Product();
        newProduct.setId(newProduct.getId());
        product.setPrice(productReg.getPrice());
        product.setQuantity(productReg.getQuantity());
        product.setName(productReg.getName());

        return "EmployeeHome";
    }

    @GetMapping("/listProducts")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());

        return "ManageProducts";
    }

    @GetMapping("/editProduct/{id}")
    public String editProductForm(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getByID(id);
        model.addAttribute("product", product);

        return "EditProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        Product newProduct = productService.getByID(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        productService.save(newProduct);

        return "redirect:/employee/loadHome";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id, Model model) {

        Product product = productService.getByID(id);
        productService.deleteProduct(product.getId());
        return "redirect:/employee/loadHome";
    }

    @GetMapping("/loadProductCheck")
    public String checkProduct(Model model) {
        model.addAttribute("product", new Supplier());
        return "EnterProductID";
    }

    @GetMapping("/getProductDetails/{id}")
    public String addProductToSupplier(@PathVariable("id") int Id, Model model) {

        Supplier supplier = supplierService.getSupplierByID(Id);
        ProductReg registration = new ProductReg();
        registration.setId(Id);
        registration.setSupplierID(Id);
        registration.setSupplier(supplier);

        model.addAttribute("supplierReg", registration);
        return "AddProductToSupplier";

    }


    @PostMapping("/saveProductToSupplier")
    public String saveProductToSupplier(@ModelAttribute("supplierReg") ProductReg productReg) {
        productService.saveProductToSupplier(productReg);
        return "redirect:/supplier/loadHome";
    }
}
