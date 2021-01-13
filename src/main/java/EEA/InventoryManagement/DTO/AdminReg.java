package EEA.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminReg {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
    private String username;
    private String password;
}
