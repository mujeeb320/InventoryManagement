package EEA.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
@JsonRootName("admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
    private int userID;

}
