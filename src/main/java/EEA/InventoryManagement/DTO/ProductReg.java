package EEA.InventoryManagement.DTO;

import EEA.InventoryManagement.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReg {
    private int id;
    private String name;
    private int quantity;
    private double price;

    private Supplier supplier;
    private int productID;
    private int supplierID;

}
