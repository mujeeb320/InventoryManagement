package EEA.InventoryManagement.DTO;

import EEA.InventoryManagement.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierReg {
    private String firstName;
    private String lastName;
    private String supplierType;
    private String address;
    private String phoneNumber;
    private String nic;
    private String username;
    private String password;

    private int supplierID;
    private int productID;

    private Supplier supplier;

    public SupplierReg(int supplierID) {
        this.supplierID = supplierID;
    }
}
