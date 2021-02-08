package EEA.InventoryManagement.DTO;

import EEA.InventoryManagement.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageReg {
    private int storageID;
    private String storageName;
    private String storageDescription;
    private int supplierID;

    private Supplier supplier;

}
