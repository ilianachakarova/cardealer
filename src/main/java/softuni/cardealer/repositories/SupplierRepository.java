package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.cardealer.models.entities.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByName(String name);
    @Query("select s from Supplier as s where s.usesImportedParts = false ")
    List<Supplier>findAllByUsesNotImportedParts();
}
