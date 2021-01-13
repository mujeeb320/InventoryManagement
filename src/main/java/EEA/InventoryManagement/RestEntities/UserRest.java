package EEA.InventoryManagement.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("userRest")
public class UserRest {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
    private String username;
    private String password;
}
