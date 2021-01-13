package EEA.InventoryManagement.Repository;

import EEA.InventoryManagement.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query("SELECT a from Admin a where a.adminID = ?1")
    Admin getByID(int id);
}
