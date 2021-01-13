package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Supplier;

import java.util.List;

public interface SupplierServiceInterface {

    List<Supplier> getAllSuppliers();

    Supplier saveSupplier(SupplierReg lawyer);

    public void save(Supplier supplier);

    Supplier getByID(int id);

    Supplier getSupplierByID(int id);

    public String deleteSupplier(int id);

}
