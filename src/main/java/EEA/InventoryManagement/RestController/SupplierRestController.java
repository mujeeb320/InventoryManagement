package EEA.InventoryManagement.RestController;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierRestController {


}

