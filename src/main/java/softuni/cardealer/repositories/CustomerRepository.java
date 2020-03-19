package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.cardealer.models.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNameAndAndDateOfBirth(String name, LocalDateTime dateOfBirth);

    @Query("select c from Customer as c order by c.dateOfBirth, c.youngDriver")
    List<Customer>getAllOrderedByDateOfBirthNotYoungDriver();
}
