package EEA.InventoryManagement.Repository;

import EEA.InventoryManagement.Entity.Product;
import EEA.InventoryManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p where p.supplier.supplierID = ?1")
    List<Product> getMyProducts(int supplierID);

    @Query("SELECT pp from Product pp where pp.id = ?1")
    Product getByID(int id);


    @Query("SELECT p from Product p where p.id = ?1")
    Product findByUserId(int id);
//    @Query("SELECT u from Product u where u.id =?1")
//    Product getByUserID(int userID);
}
