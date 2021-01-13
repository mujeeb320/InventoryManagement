package EEA.InventoryManagement.Service;


import EEA.InventoryManagement.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    public User save(User user);


    User findId(int user_id);

    User searchByUsername(String username);

    List<User> getAll();

    User findByTableID(int userID);

    void deleteByTableID(int id);

    public void deleteSupplier(int id);

    public void deleteAdmin(int id);

    public void deleteEmployee(int id);

    void deleteAdminUser(User user);

    void deleteEmployeeUser(User user);

    void deleteSupplierUser(User user);
}
