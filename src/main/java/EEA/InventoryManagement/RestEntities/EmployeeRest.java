package EEA.InventoryManagement.RestEntities;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonRootName("employeeRest")
public class EmployeeRest {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String nic;
}
