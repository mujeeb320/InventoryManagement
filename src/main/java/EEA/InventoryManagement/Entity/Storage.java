package EEA.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
@JsonRootName("storage")
public class Storage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int storageID;
    private String storageName;
    private String storageDescription;

    @JsonIgnore
    @ManyToMany(mappedBy = "storageList")
    List<Supplier> supplierList;


}
