package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.EmployeeReg;
import EEA.InventoryManagement.Entity.Employee;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(EmployeeReg employeeReg) {
        Employee employee = new Employee();
        employee.setFirstName(employeeReg.getFirstName());
        employee.setLastName(employeeReg.getLastName());
        employee.setNic(employeeReg.getNic());
        employee.setAddress(employeeReg.getAddress());
        employee.setPhoneNumber(employeeReg.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    public void save(Employee savedEmployee) {
        employeeRepository.save(savedEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee getByID(int id) {
        return employeeRepository.getOne(id);
    }



    @Override
    public String deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return  +id+ " has been removed Successfully";
    }
}

