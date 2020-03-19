package softuni.cardealer.models.entities.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDto {
    @XmlElement(name = "supplier")
    private List<SupplierSeedDto>suppliers;

    public SupplierSeedRootDto() {
    }

    public List<SupplierSeedDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }
}
