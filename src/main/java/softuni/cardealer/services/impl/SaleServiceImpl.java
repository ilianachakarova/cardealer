package softuni.cardealer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.models.entities.Sale;
import softuni.cardealer.repositories.SaleRepository;
import softuni.cardealer.services.CarService;
import softuni.cardealer.services.CustomerService;
import softuni.cardealer.services.SaleService;

import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final Random random;

    private final static double[] DISCOUNT = new double[] {0, 0.5,0.10,0.15,0.20,0.30,0.40,0.50};

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, Random random) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.random = random;
    }

    @Override
    public void seedSales() {

        for (int i = 0; i <20 ; i++) {
            Sale sale = new Sale();
            int randomIndex = random.nextInt(8);
            sale.setDiscount(DISCOUNT[randomIndex]);
            sale.setCar(this.carService.getRandomCar());
            sale.setCustomer(this.customerService.getRandomCustomer());
            System.out.println();
            this.saleRepository.saveAndFlush(sale);
        }

    }
}
