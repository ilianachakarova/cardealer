package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.cardealer.models.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNameAndAndDateOfBirth(String name, LocalDateTime dateOfBirth);

    List<Customer>findAllByDateOfBirthAndYoungDriver(LocalDateTime date, boolean isYoung);
}
