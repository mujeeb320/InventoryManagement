package EEA.InventoryManagement.RestController;

import EEA.InventoryManagement.DTO.AdminReg;
import EEA.InventoryManagement.DTO.EmployeeReg;
import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.*;
import EEA.InventoryManagement.RestEntities.LoginRest;
import EEA.InventoryManagement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AdminService adminService;
    @Autowired
    LoginService loginService;
    @Autowired
    EmployeeService employeeService;


    @PostMapping("/login")
    public String userLogin(@RequestBody LoginRest user) {
        try {
            String check = loginService.login(user);
            return check;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/saveSupplier")
    public String saveSupplier(@RequestBody SupplierReg supplierReg)
    {

        Supplier savedSupplier = supplierService.saveSupplier(supplierReg);
        User user = new User();
        user.setTableID(savedSupplier.getSupplierID());
        user.setFirstName(supplierReg.getFirstName());
        user.setLastName(supplierReg.getLastName());
        user.setUsername(supplierReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(supplierReg.getPassword()));
        user.setNic(supplierReg.getNic());
        user.setAddress(supplierReg.getAddress());
        user.setPhoneNumber(supplierReg.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Supplier")));
        User savedUser = userService.save(user);
        savedSupplier.setUserID(savedUser.getUserID());
        supplierService.save(savedSupplier);

        return "Supplier Account Created Successfully";
    }



    @PostMapping("/saveAdmin")
    public String saveAdmin(@RequestBody AdminReg adminReg)
    {

        Admin savedAdmin = adminService.saveAdmin(adminReg);
        User user = new User();
        user.setTableID(savedAdmin.getAdminID());
        user.setFirstName(adminReg.getFirstName());
        user.setLastName(adminReg.getLastName());
        user.setUsername(adminReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(adminReg.getPassword()));
        user.setPhoneNumber(adminReg.getPhoneNumber());
        user.setAddress(adminReg.getAddress());
        user.setNic(adminReg.getNic());
        user.setRoles(Arrays.asList(new Role("Admin")));
        User savedUser = userService.save(user);
        savedAdmin.setUserID(savedUser.getUserID());
        adminService.save(savedAdmin);

        return "Admin Account Created Successfully";
    }

    @PostMapping("/registerNewEmployee")
    public String registerEmployee(@RequestBody EmployeeReg employeeReg) {

        Employee registerEmployee = employeeService.saveEmployee(employeeReg);

        User user = new User();
        user.setTableID(registerEmployee.getEmployeeID());
        user.setFirstName(employeeReg.getFirstName());
        user.setLastName(employeeReg.getLastName());
        user.setUsername(employeeReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(employeeReg.getPassword()));
        user.setAddress(employeeReg.getAddress());
        user.setNic(employeeReg.getNic());
        user.setPhoneNumber(employeeReg.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Employee")));
        User savedUser = userService.save(user);
        registerEmployee.setUserID(savedUser.getUserID());
        employeeService.save(registerEmployee);

        return "Employee Account Created Successfully";

    }

}
