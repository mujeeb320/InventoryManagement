package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.DTO.AdminReg;
import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Admin;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.Role;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Service.AdminService;
import EEA.InventoryManagement.Service.LoginService;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {

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

    @GetMapping("/registerSupplier")
    public String loadSupplierRegister(Model model)
    {
        model.addAttribute("supplier",new SupplierReg());
        return "RegisterSuppliers";
    }

    @PostMapping("/saveSupplier")
    public String saveSupplier(@ModelAttribute("supplier") SupplierReg supplierReg)
    {

        Supplier savedSupplier = supplierService.saveSupplier(supplierReg);
        User user = new User();
        user.setTableID(savedSupplier.getSupplierID());
        user.setFirstName(supplierReg.getFirstName());
        user.setLastName(supplierReg.getLastName());
        user.setUsername(supplierReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(supplierReg.getPassword()));
        user.setPhoneNumber(supplierReg.getPhoneNumber());
        user.setNic(supplierReg.getNic());
        user.setAddress(supplierReg.getAddress());
        user.setRoles(Arrays.asList(new Role("Supplier")));
        User savedUser = userService.save(user);
        savedSupplier.setUserID(savedUser.getUserID());
        supplierService.save(savedSupplier);

       return "redirect:/admin/listEditDeleteSuppliers?success";
    }

    @GetMapping("/registerAdmin")
    public String loadAdminRegister(Model model)
    {
        model.addAttribute("admin",new AdminReg());
        return "RegisterAdmin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin")AdminReg adminReg)
    {

        Admin savedAdmin = adminService.saveAdmin(adminReg);
        User user = new User();
        user.setTableID(savedAdmin.getAdminID());
        user.setFirstName(adminReg.getFirstName());
        user.setLastName(adminReg.getLastName());
        user.setUsername(adminReg.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(adminReg.getPassword()));
        user.setPhoneNumber(adminReg.getPhoneNumber());
        user.setNic(adminReg.getNic());
        user.setAddress(adminReg.getAddress());
        user.setRoles(Arrays.asList(new Role("Admin")));
        User savedUser = userService.save(user);
        savedAdmin.setUserID(savedUser.getUserID());
        adminService.save(savedAdmin);

        return "redirect:/user/registerAdmin?success";
    }


    @GetMapping("/viewSupplier/{supplier_ID}")
    public Supplier GetSupplier(@PathVariable(value = "supplier_ID") int supplier_ID)
    {
        Supplier supplier = supplierService.getSupplierByID(supplier_ID);
        return supplier;
    }

//    @PostMapping("/updateSupplier")
//    public String updateSupplier(@RequestBody Supplier supplier,@RequestHeader("account-id") int accountId)
//    {
//
//        loginService.CreatCurrentSessionSupplier(accountId);
//        User k = userService.getCurrentUser();
//        if(userService.getCurrentUser().getUser_ID()!=user.getUser_ID())
//        {
//            User currentUser = userService.findById(user.getUser_ID());
//            currentUser.setUser_username(user.getUser_username());
//            currentUser.setUser_email(user.getUser_email());
//            boolean task = userService.saveUpdateUser(currentUser);
//            if(task==true)
//            {
//                Json_Message message = new Json_Message("Updated");
//                return message;
//            }
//            else
//            {
//                Json_Message message = new Json_Message("Failed");
//                return message;
//            }
//        }
//        Json_Message message = new Json_Message("You cannot Update your own account !!!\nPlease use my profile to update your account.");
//        return message;
//    }
}

