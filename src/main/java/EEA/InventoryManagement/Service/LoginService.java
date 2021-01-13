package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Role;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Entity.User;
import EEA.InventoryManagement.RestEntities.LoginRest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService implements LoginServiceInterface{

    @Autowired
    UserService userService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String login(LoginRest loginRest) {
        try{
            User check = userService.searchByUsername(loginRest.getUsername());

            if (check != null) {

                if(bCryptPasswordEncoder.matches(loginRest.getPassword(),check.getPassword()))
                {
                    User user = check;

                    return ((user.getRoles().stream().findFirst()).get()).getRole()+" "+ user.getUserID()+ " "+user.getUsername();
                }
                else
                {
                    return "Invalid";
                }


            }
            else {
                return "Invalid";
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

//    @Override
//    public void CreatCurrentSessionSupplier(int id) {
//        try
//        {
//            SupplierReg supplierReg = supplierService.getSupplierRegByID(id);
//            Authentication auth = new UsernamePasswordAuthenticationToken(supplierReg.getUsername(), supplierReg.getPassword());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

}
