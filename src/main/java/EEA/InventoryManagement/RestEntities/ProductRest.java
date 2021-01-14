package EEA.InventoryManagement.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("ProductRest")
public class ProductRest {

    private int id;
    private String name;
    private int quantity;
    private Double price;
}
