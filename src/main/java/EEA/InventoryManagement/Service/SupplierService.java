package EEA.InventoryManagement.Service;

import EEA.InventoryManagement.DTO.ProductReg;
import EEA.InventoryManagement.DTO.SupplierReg;
import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements SupplierServiceInterface {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;

    @Override
    public List<Supplier> getAllSuppliers() {

        return supplierRepository.findAll();
    }


    @Override
    public Supplier saveSupplier(SupplierReg supplierReg) {

        Supplier tempSupplier = new Supplier();
        tempSupplier.setFirstName(supplierReg.getFirstName());
        tempSupplier.setLastName(supplierReg.getLastName());
        tempSupplier.setAddress(supplierReg.getAddress());
        tempSupplier.setPhoneNumber(supplierReg.getPhoneNumber());
        tempSupplier.setNic(supplierReg.getNic());
        tempSupplier.setType(supplierReg.getSupplierType());
        return this.supplierRepository.save(tempSupplier);

    }
    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }


    @Override
    public Supplier getByID(int id) {
        return supplierRepository.getOne(id);
    }

    @Override
    public Supplier getSupplierByID(int id) {
        return supplierRepository.getSupplierID(id);
    }

    @Override
    public String deleteSupplier(int id) {
         supplierRepository.deleteById(id);
        return  +id+ " has been removed Successfully";
    }


//    @Autowired
//    private SupplierRepository repository;
//
//    public Supplier saveSupplier(Supplier supplier)
//    {
//        return repository.save(supplier);
//    }
//
//    public List<Supplier> saveSupplier(List<Supplier> supplier)
//    {
//        return repository.saveAll(supplier);
//    }
//
//    public List<Supplier> getSupplier()
//    {
//        return repository.findAll();
//    }
//
//    public Supplier getSupplierByID (int id)
//    {
//        return repository.findById(id).orElse(null);
//    }

//    public Supplier getSupplierByFirstName(String firstName)
//    {
//        return repository.findByFirstName(firstName);
//    }

//    public Supplier getSupplierByLastName(String lastName)
//    {
//        return repository.findByLastName(lastName);
//    }

//    public Supplier getSupplierBySupplierType(String supplierType)
//    {
//        return repository.findBySupplierType(supplierType);
//    }

//    public String deleteSupplier (int id)
//    {
//        repository.deleteById(id);
//        return  +id+ " has been removed Successfully";
//    }
//
    public Supplier updateSupplier (Supplier supplier)
    {
        Supplier existingSupplier = supplierRepository.findById(supplier.getSupplierID()).orElse(null);
        existingSupplier.setFirstName(supplier.getFirstName());
        existingSupplier.setLastName(supplier.getLastName());
        existingSupplier.setAddress(supplier.getAddress());

        return supplierRepository.save(existingSupplier);
    }

//    public Supplier getSupplierByID(int userID) {
//        return supplierRepository.getByUserID(userID);
//    }


}
