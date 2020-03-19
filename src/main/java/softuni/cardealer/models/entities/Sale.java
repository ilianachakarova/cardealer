package softuni.cardealer.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    private double discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }
    @Column(name = "discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
       this.discount = discount;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
