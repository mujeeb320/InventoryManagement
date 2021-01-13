package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.Entity.Role;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.Repository.AdminRepository;
import EEA.InventoryManagement.Repository.EmployeeRepository;
import EEA.InventoryManagement.Repository.SupplierRepository;
import EEA.InventoryManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findId(int user_id) {
        return userRepository.findByUserId(user_id);
    }


    @Override
    public User searchByUsername(String username) {
        return userRepository.searchUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByTableID(int userID) {
        return userRepository.getOne(userID);
    }

    @Override
    public void deleteByTableID(int id) {
        User user= userRepository.getByTableID(id);
        userRepository.delete(user);
    }

    @Override
    public void deleteSupplier(int id){
        userRepository.deleteById(id);
    }

    @Override
    public void deleteEmployee(int id){
        userRepository.deleteById(id);
    }


    @Override
    public void deleteAdmin(int id){
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAdminUser(User user) {
        userRepository.deleteById(user.getUserID());
        adminRepository.deleteById(user.getTableID());
    }

    @Override
    public void deleteEmployeeUser(User user) {
        userRepository.deleteById(user.getUserID());
        employeeRepository.deleteById(user.getTableID());
    }

    @Override
    public void deleteSupplierUser(User user) {
        userRepository.deleteById(user.getUserID());
        supplierRepository.deleteById(user.getTableID());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.searchUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
