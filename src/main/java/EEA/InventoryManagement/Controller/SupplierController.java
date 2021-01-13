package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Service.ProductService;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;


    @RequestMapping("/loadHome")
    public String loadHome(Model model)
    {
        String username;


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchByUsername(username);

        Supplier supplier = supplierService.getSupplierByID(user.getTableID());

        List<Product> productList = productService.getMyProducts(supplier.getSupplierID());
        model.addAttribute("products", productList);

        return "SupplierHome";
    }

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/addSupplier")
    public Supplier addSupplier (@RequestBody SupplierReg supplier)
    {
        return supplierService.saveSupplier(supplier);
    }


    @GetMapping("/suppliers")
    public List <Supplier> findAllSuppliers()
    {
        return supplierService.getAllSuppliers();
    }

    @PostMapping("/supplier/{id}")
    public Supplier findSupplierByID(@PathVariable int id)
    {
        return supplierService.getByID(id);
    }


    @PutMapping("/update")
    public Supplier updateSupplier (@RequestBody Supplier supplier)
    {
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable int id)
    {
        return supplierService.deleteSupplier(id);
    }



}
