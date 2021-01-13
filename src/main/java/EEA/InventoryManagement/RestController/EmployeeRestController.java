package EEA.InventoryManagement.RestController;

import EEA.InventoryManagement.DTO.EmployeeReg;
import EEA.InventoryManagement.Entity.Employee;
import EEA.InventoryManagement.Entity.Role;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Service.EmployeeService;
import EEA.InventoryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {



}
