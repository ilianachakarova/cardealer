package softuni.cardealer.services;

import softuni.cardealer.models.entities.Customer;
import softuni.cardealer.models.entities.dtos.CustomerSeedDto;
import softuni.cardealer.models.entities.dtos.CustomerViewRootDto;

import java.util.List;

public interface CustomerService {
    void seedCustomers(List<CustomerSeedDto> customerSeedDtos);
    Customer getRandomCustomer();
    CustomerViewRootDto getAllOrderedCustomers();
    }
