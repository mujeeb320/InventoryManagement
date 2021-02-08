package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.StorageReg;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Storage;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService implements StorageServiceInterface{

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    SupplierService supplierService;


    @Override
    public Storage createStorage(StorageReg storageReg) {
        Storage storage = new Storage();
         storage.setStorageName(storageReg.getStorageName());
        storage.setStorageDescription(storageReg.getStorageDescription());
        return storageRepository.save(storage);
    }

    @Override
    public List<Storage> getAllStorages() {
        return storageRepository.findAll();
    }

    @Override
    public Storage getStorageByID(int id) {
        return storageRepository.getOne(id);
    }
    @Override
    public boolean addSupplierToStorage(StorageReg storageReg) {

        Storage storage = storageService.getStorageByID(storageReg.getStorageID());

        Supplier supplier  = supplierService.getSupplierByID(storageReg.getSupplierID());


        if (storage != null && supplier != null)
        {
            List<Storage> storageList = new ArrayList<>();
            storageList = supplier.getStorageList();
            storageList.add(storage);
            supplier.setStorageList(storageList);
            List<Supplier> supplierList = new ArrayList<>();
            supplierList= storage.getSupplierList();
            supplierList.add(supplier);
            storage.setSupplierList(supplierList);
            storageRepository.save(storage);
            return true;
        }

        return false;
    }
}
