package EEA.InventoryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReg {
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String phoneNumber;
    private String username;
    private String password;
}
