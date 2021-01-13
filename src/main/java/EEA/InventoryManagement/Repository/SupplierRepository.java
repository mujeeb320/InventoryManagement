package EEA.InventoryManagement.Repository;

import EEA.InventoryManagement.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT u from Supplier u where u.userID=?1")
    Supplier getByUserID(int userID);

    @Query("SELECT s from Supplier s where s.supplierID=?1")
    Supplier getSupplierID(int id);

//    Supplier findByFirstName(String firstName);
//    Supplier findByLastName(String lastName);
//    Supplier findBySupplierType(String supplierType);

}
