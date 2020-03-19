package softuni.cardealer.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String name;
    private LocalDateTime dateOfBirth;
    private boolean isYoungDriver;
    private List<Sale> sales;


    public Customer() {
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "date_of_birth")
    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Column(name = "is_young_driver")
    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {

        isYoungDriver = youngDriver;
    }
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
