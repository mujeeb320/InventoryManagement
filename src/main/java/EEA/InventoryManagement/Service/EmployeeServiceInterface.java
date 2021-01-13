package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.EmployeeReg;
import EEA.InventoryManagement.Entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee saveEmployee(EmployeeReg employeeReg);

    List<Employee> getAllEmployees();

    Employee getByID(int id);

     String deleteEmployee(int id);
}

