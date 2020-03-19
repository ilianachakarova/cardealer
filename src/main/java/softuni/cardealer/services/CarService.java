package softuni.cardealer.services;

import softuni.cardealer.models.entities.Car;
import softuni.cardealer.models.entities.dtos.CarSeedDto;
import softuni.cardealer.models.entities.dtos.CarViewRootDto;

import java.util.List;

public interface CarService {
    void seedCars(List<CarSeedDto>carSeedDtos);
    Car getRandomCar();
    CarViewRootDto writeCarsByMakeToyota();
    CarViewRootDto getAllCarsAndParts();
}
