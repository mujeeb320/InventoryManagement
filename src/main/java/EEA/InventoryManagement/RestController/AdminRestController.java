package EEA.InventoryManagement.RestController;

import EEA.InventoryManagement.Entity.*;
import EEA.InventoryManagement.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;



    @GetMapping("/allEmployees")
    public List<Employee> AllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/allAdmins")
    public List<Admin> AllAdmins()
    {
        return adminService.getAllAdmins();
    }

    @GetMapping("/allSuppliers")
    public List<Supplier> AllSuppliers() { return supplierService.getAllSuppliers(); }

    @GetMapping("/viewUserDetails/{user_ID}")
    public User ViewUser(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findId(user_ID);
        return user;
    }

        @PostMapping("/editAdmin")
        public String updateAdmin(@RequestBody User user)
        {
            userService.save(user);
            Admin admin = adminService.getByAdminID(user.getTableID());
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setAddress(user.getAddress());
            admin.setPhoneNumber(user.getPhoneNumber());
            admin.setNic(user.getNic());
            adminService.save(admin);
            String message = "Updated";
            return message;
        }

    @GetMapping("/deleteUser/{user_ID}")
    public String DeleteUser(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteAdminUser(user);
        return "User Deleted";
    }

    @GetMapping("/viewSupplierUserDetails/{user_ID}")
    public User ViewSupplierUser(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findId(user_ID);
        return user;
    }

    @PostMapping("/updateSupplier")
    public String updateSupplier(@RequestBody User user)
    {
        userService.save(user);
        Supplier supplier = supplierService.getSupplierByID(user.getTableID());
        supplier.setFirstName(user.getFirstName());
        supplier.setLastName(user.getLastName());
        supplier.setAddress(user.getAddress());
        supplier.setPhoneNumber(user.getPhoneNumber());
        supplier.setNic(user.getNic());
        supplierService.save(supplier);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteSupplier/{user_ID}")
    public String DeleteSupplier(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteSupplierUser(user);
        return "User Deleted";
    }

    @GetMapping("/viewEmployeeUserDetails/{user_ID}")
    public User ViewEmployeeUser(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findId(user_ID);
        return user;
    }

    @PostMapping("/updateEmployee")
    public String saveEmployee(@RequestBody User user)
    {
        userService.save(user);
        Employee employee = employeeService.getByID(user.getTableID());
        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setAddress(user.getAddress());
        employee.setPhoneNumber(user.getPhoneNumber());
        employee.setNic(user.getNic());
        employeeService.save(employee);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteEmployee/{user_ID}")
    public String DeleteEmployee(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteEmployeeUser(user);
        return "User Deleted";
    }
}
