package softuni.cardealer.services;

import softuni.cardealer.models.entities.Supplier;
import softuni.cardealer.models.entities.dtos.SupplierSeedDto;
import softuni.cardealer.models.entities.dtos.SupplierViewRootDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface SupplierService {
    void seedSuppliers(List<SupplierSeedDto>supplierSeedDtos) throws JAXBException, FileNotFoundException;
    Supplier getRandomSupplier();
    SupplierViewRootDto getLocalSuppliers();
}
