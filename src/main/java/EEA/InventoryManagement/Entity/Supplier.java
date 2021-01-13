package EEA.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
@JsonRootName("supplier")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int supplierID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
    private String type;
    private int userID;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Product.class)
    private List<Product> productList;
}
