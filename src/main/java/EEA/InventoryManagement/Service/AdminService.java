package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.AdminReg;
import EEA.InventoryManagement.Entity.Admin;
import EEA.InventoryManagement.Entity.Employee;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface{
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(AdminReg adminReg) {
        Admin admin = new Admin();
        admin.setFirstName(adminReg.getFirstName());
        admin.setLastName(adminReg.getLastName());
        admin.setAddress(adminReg.getAddress());
        admin.setPhoneNumber(adminReg.getPhoneNumber());
        admin.setNic(adminReg.getNic());
        return adminRepository.save(admin);
    }
    @Override
    public void save(Admin savedAdmin) {
        adminRepository.save(savedAdmin);
    }

    @Override
    public List<Admin> getAllAdmins() {

        return adminRepository.findAll();
    }

    @Override
    public Admin getByID(int id) {
        return adminRepository.getOne(id);
    }

    @Override
    public String deleteAdmin(int id) {
        adminRepository.deleteById(id);
        return  +id+ " has been removed Successfully";
    }

    @Override
    public Admin getByAdminID(int id) {
        return adminRepository.getByID(id);
    }

//
//    @GetMapping("/admins")
//    public List<Admin> findAllAdmins()
//    {
//        return adminRepository.();
//    }

}


