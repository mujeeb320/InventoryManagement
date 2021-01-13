package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.DTO.AdminReg;
import EEA.InventoryManagement.Entity.*;
import EEA.InventoryManagement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AdminService adminService;
    @Autowired
    ProductService productService;


    @GetMapping("/loadHome")
    public String loadHome(Model model)
    {
        model.addAttribute("users", userService.getAll());
        return "AdminHome";
    }

    @GetMapping("/listEditDeleteSuppliers")
    public String editSupplier(Model model)
    {
        model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
        return "ManageSuppliers";
    }

    @GetMapping("/editSupplier/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        Supplier supplier = supplierService.getByID(id);
        model.addAttribute("supplier", supplier);
        return "EditSupplier";
    }

    @GetMapping("/deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable( value = "id") int id) {
        Supplier supplier=supplierService.getByID(id);
        userService.deleteSupplier(supplier.getUserID());
        supplierService.deleteSupplier(id );
        return "redirect:/admin/listEditDeleteSuppliers?success";
    }

    @PostMapping("/updateSupplier")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier)
    {

        User user = userService.findByTableID(supplier.getUserID());
        user.setFirstName(supplier.getFirstName());
        user.setLastName(supplier.getLastName());
        userService.save(user);
        supplierService.save(supplier);
        return "redirect:/admin/listEditDeleteSuppliers?success";
    }

    @GetMapping("/viewAllEmployees")
    public String viewAllEmployees(Model model)
    {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "ManageEmployees";
    }

    @GetMapping("/viewAllAdmins")
    public String viewAllAdmins(Model model)
    {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "ManageAdmins";
    }

    @GetMapping("/editAdmin/{id}")
    public String showAdminFormForUpdate(@PathVariable( value = "id") int id, Model model) {
        Admin admin = adminService.getByID(id);
        model.addAttribute("admin", admin);
        return "EditAdmin";
    }

    @PostMapping("/updateAdmin")
    public String saveAdmin(@ModelAttribute("admin") Admin admin)
    {

        User user = userService.findByTableID(admin.getUserID());
        user.setFirstName(admin.getFirstName());
        user.setLastName(admin.getLastName());
        user.setAddress(admin.getAddress());
        user.setPhoneNumber(admin.getPhoneNumber());
        user.setNic(admin.getNic());
        userService.save(user);
        adminService.save(admin);
        return "redirect:/admin/viewAllAdmins?updatesuccess";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable( value = "id") int id) {
        Admin admin=adminService.getByID(id);
        userService.deleteAdmin(admin.getUserID());
        adminService.deleteAdmin(id);
        return "redirect:/admin/viewAllAdmins?deletesuccess";
    }

    @GetMapping("/editEmployee/{id}")
    public String showEmployeeFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        Employee employee = employeeService.getByID(id);
        model.addAttribute("employee", employee);
        return "EditEmployee";
    }

    @PostMapping("/updateEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {

        User user = userService.findByTableID(employee.getUserID());
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        user.setAddress(employee.getAddress());
        user.setNic(employee.getNic());
        user.setPhoneNumber(employee.getPhoneNumber());
        userService.save(user);
        employeeService.save(employee);
        return "redirect:/admin/viewAllEmployees?success";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable( value = "id") int id) {
        Employee employee=employeeService.getByID(id);
        userService.deleteEmployee(employee.getUserID());
        employeeService.deleteEmployee(id);
        return "redirect:/admin/viewAllEmployees?deleteSuccess";
    }

    @GetMapping("/viewAllProducts")
    public String viewAllProducts(Model model)
    {
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
        return "redirect:/admin/viewAllProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id, Model model) {

        Product product = productService.getByID(id);
        productService.deleteProduct(product.getId());
        return "redirect:/admin/viewAllProducts?deletesuccess";
    }
}
