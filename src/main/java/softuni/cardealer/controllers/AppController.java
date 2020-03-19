package softuni.cardealer.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.cardealer.constant.GlobalConstants;
import softuni.cardealer.models.entities.dtos.*;
import softuni.cardealer.services.*;
import softuni.cardealer.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Component
public class AppController implements CommandLineRunner {
    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public AppController(XmlParser xmlParser, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();

        this.writeCustomers();
        this.writeCarsMakeToyota();
        this.writeLocalSuppliers();
        this.writeCarsWithParts();


    }


    private void writeCarsWithParts() throws JAXBException {
        CarViewRootDto carViewRootDto = this.carService.getAllCarsAndParts();
        this.xmlParser.marshalToFile(GlobalConstants.OUTPUT_4, carViewRootDto);
    }

    private void writeLocalSuppliers() throws JAXBException {
        SupplierViewRootDto supplierViewRootDto = this.supplierService.getLocalSuppliers();
        this.xmlParser.marshalToFile(GlobalConstants.OUTPUT_3, supplierViewRootDto);
    }

    private void writeCarsMakeToyota() throws JAXBException {
        CarViewRootDto carViewRootDto = this.carService.writeCarsByMakeToyota();
        this.xmlParser.marshalToFile(GlobalConstants.OUTPUT_2, carViewRootDto);
    }

    private void writeCustomers() throws JAXBException {
        CustomerViewRootDto customerViewRootDto = this.customerService.getAllOrderedCustomers();
        this.xmlParser.marshalToFile(GlobalConstants.OUTPUT_1, customerViewRootDto);
    }

    private void seedSales() {
        this.saleService.seedSales();
    }

    private void seedCustomers() throws JAXBException, FileNotFoundException {
        CustomerSeedRootDto customerSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.CUSTOMERS_XML_IMPORT, CustomerSeedRootDto.class);
        this.customerService.seedCustomers(customerSeedRootDto.getCustomers());


    }

    private void seedCars() throws JAXBException, FileNotFoundException {
        CarSeedRootDto carSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.CARS_XML_IMPORT, CarSeedRootDto.class);
        this.carService.seedCars(carSeedRootDto.getCars());
    }

    private void seedParts() throws JAXBException, FileNotFoundException {
        PartSeedRootDto partSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.PARTS_XML_IMPORT, PartSeedRootDto.class);
        this.partService.seedParts(partSeedRootDto.getParts());
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierSeedRootDto supplierSeedRootDto =
                this.xmlParser.unmarshalFromFile(GlobalConstants.SUPPLIERS_XML_IMPORT, SupplierSeedRootDto.class);
        this.supplierService.seedSuppliers(supplierSeedRootDto.getSuppliers());
    }
}
