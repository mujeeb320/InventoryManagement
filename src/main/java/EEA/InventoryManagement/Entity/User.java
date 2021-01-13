package EEA.InventoryManagement.Entity;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@JsonRootName("user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userID;
    private int tableID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
    private String username;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(
                    name = "user_ID", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_ID", referencedColumnName = "roleID"))
    private Collection<Role> roles;
}
