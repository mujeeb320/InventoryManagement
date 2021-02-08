package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.StorageReg;
import EEA.InventoryManagement.Entity.Storage;

import java.util.List;

public interface StorageServiceInterface {
    Storage createStorage(StorageReg storageReg);

    List<Storage> getAllStorages();

    Storage getStorageByID(int id);

    boolean addSupplierToStorage(StorageReg storageReg);
}
