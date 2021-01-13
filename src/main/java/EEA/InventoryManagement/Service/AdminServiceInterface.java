package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.AdminReg;
import EEA.InventoryManagement.Entity.Admin;

import java.util.List;

public interface AdminServiceInterface {

    public void save(Admin savedAdmin);
    public Admin saveAdmin(AdminReg adminReg);
    public List<Admin> getAllAdmins();
    public Admin getByID(int id);
    public String deleteAdmin(int id);

    Admin getByAdminID(int id);
}
