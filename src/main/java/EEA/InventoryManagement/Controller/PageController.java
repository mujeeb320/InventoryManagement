package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Service.SupplierService;
import EEA.InventoryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @Autowired
    UserService userService;
    @Autowired
    SupplierService supplierService;


    @GetMapping("/login")
    public String LoadLoginPage()
    {
        return "login";
    }


    @GetMapping("/error")
    public String LoadErrorPage()
    {
        return "error";
    }


    @GetMapping("/")
    public String viewHomePage (Model model) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user=userService.searchByUsername(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Admin")))
        {
            model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
            return "redirect:/admin/loadHome";
        }

        if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Employee")))
        {
            model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
            return "redirect:/employee/loadHome";
        }
        if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Supplier")))
        {
            model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
            return "redirect:/supplier/loadHome";
        }
            return "home";
    }
}
