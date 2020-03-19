package softuni.cardealer.models.entities.dtos;

import softuni.cardealer.adaptors.LocalDateTimeAdaptor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {
    @XmlAttribute
    private String name;
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    @XmlElement(name = "birth-date")
    private LocalDateTime birthDate;
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
