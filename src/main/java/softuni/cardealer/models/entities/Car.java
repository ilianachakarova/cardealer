package softuni.cardealer.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    private String make;
    private String model;
    private BigDecimal distance;
    private List<Part> parts;
    private List<Sale>sales;


    public Car() {
    }
    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column(name = "distance")
    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    @ManyToMany()
    @JoinTable(name = "cars_parts")
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "car",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
