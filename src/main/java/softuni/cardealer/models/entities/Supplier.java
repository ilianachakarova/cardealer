package softuni.cardealer.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity{
    private String name;
    private boolean usesImportedParts;
    private List<Part>parts;

    public Supplier() {
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "is_uses_imported_parts")
    public boolean isUsesImportedParts() {
        return usesImportedParts;
    }

    public void setUsesImportedParts(boolean usesImportedParts) {
        this.usesImportedParts = usesImportedParts;
    }
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
