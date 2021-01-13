package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.DTO.EmployeeReg;
import EEA.InventoryManagement.Entity.*;
import EEA.InventoryManagement.Service.EmployeeService;
import EEA.InventoryManagement.Service.ProductService;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loadHome")
    public String loadHome()
    {
        return "EmployeeHome";
    }

    @GetMapping("/registerEmployee")
    public String loadEmployeeRegister(Model model) {
        model.addAttribute("employee", new EmployeeReg());

        return "RegisterEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeReg employeeReg) {

        Employee saveEmployee = employeeService.saveEmployee(employeeReg);
        User user = new User();
        user.setTableID(saveEmployee.getEmployeeID());
        user.setFirstName(employeeReg.getFirstName());
        user.setLastName(employeeReg.getLastName());
        user.setUsername(employeeReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(employeeReg.getPassword()));
        user.setAddress(employeeReg.getAddress());
        user.setNic(employeeReg.getNic());
        user.setPhoneNumber(employeeReg.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Employee")));
        User savedUser = userService.save(user);
        saveEmployee.setUserID(savedUser.getUserID());
        employeeService.save(saveEmployee);

        return "redirect:/admin/viewAllEmployees?Registersuccess";
    }

    @GetMapping("/ViewAllProducts")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());

        return "ViewAllProductsforemp";
    }

    @GetMapping("/editProduct/{id}")
    public String editProductForm(@PathVariable(value = "id") int id, Model model) {
        Product product = productService.getByID(id);
        model.addAttribute("product", product);
        return "EditProductemp";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        Product newProduct = productService.getByID(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        productService.save(newProduct);
        return "redirect:/employee/ViewAllProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id, Model model) {

        Product product = productService.getByID(id);
        productService.deleteProduct(product.getId());
        return "redirect:/employee/ViewAllProducts";
    }

    @GetMapping("/viewSuppliers")
    public String ViewSuppliers(Model model)
    {
        model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
        return "ManageSuppliersForProducts";
    }

}
