package softuni.cardealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.models.entities.Customer;
import softuni.cardealer.models.entities.dtos.CustomerSeedDto;
import softuni.cardealer.models.entities.dtos.CustomerViewDto;
import softuni.cardealer.models.entities.dtos.CustomerViewRootDto;
import softuni.cardealer.repositories.CustomerRepository;
import softuni.cardealer.services.CustomerService;
import softuni.cardealer.utils.ValidationUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CustomerRepository customerRepository;
    private final Random random;


    @Autowired
    public CustomerServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, CustomerRepository customerRepository, Random random) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.customerRepository = customerRepository;
        this.random = random;
    }

    @Override
    public void seedCustomers(List<CustomerSeedDto> customerSeedDtos) {
        customerSeedDtos.forEach(customerSeedDto -> {
            if(this.validationUtil.isValid(customerSeedDto)){
                if(this.isExistCustomer(customerSeedDto.isYoungDriver(),customerSeedDto.getBirthDate())){
                    System.out.println("Customer already in DB");
                }else {

                    Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
                    customer.setDateOfBirth(customerSeedDto.getBirthDate());
                    this.customerRepository.saveAndFlush(customer);

                }
            }else {
                this.validationUtil.violations(customerSeedDto).stream().forEach(v-> System.out.println(v.getMessage()));
            }
        });
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = this.random.nextInt((int) this.customerRepository.count())+1;
        return this.customerRepository.findById(randomId).orElse(null);
    }

    @Override
    public CustomerViewRootDto getAllOrderedCustomers() {
        List<Customer>customers = this.customerRepository.findAll();
        List<CustomerViewDto>customerViewDtos = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerViewDto customerViewDto = this.modelMapper.map(customer,CustomerViewDto.class);
            customerViewDtos.add(customerViewDto);
        });
        CustomerViewRootDto customerViewRootDto = new CustomerViewRootDto();
        customerViewRootDto.setCustomers(customerViewDtos);
        return customerViewRootDto;
    }


    private boolean isExistCustomer(boolean name, LocalDateTime birthDate) {
        return this.customerRepository.findAllByDateOfBirthAndYoungDriver(birthDate,name) != null;
    }
}
