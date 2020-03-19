package softuni.cardealer.models.entities.dtos;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewDto {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private BigDecimal distanceTravelled;
    @XmlElement(name = "parts")
    private PartViewRootDto parts;

    public CarViewDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(BigDecimal distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public PartViewRootDto getParts() {
        return parts;
    }

    public void setParts(PartViewRootDto parts) {
        this.parts = parts;
    }
}
