package EEA.InventoryManagement.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("adminRest")
public class AdminRest {
    private int adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;

}
