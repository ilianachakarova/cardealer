package softuni.cardealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.cardealer.models.entities.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findByMakeAndModel(String make, String model);
    @Query("select c from Car as c where c.make =:name order by c.model asc , c.distance desc ")
    List<Car>getAllByMakeToyota(@Param(value = "name") String name);

}
