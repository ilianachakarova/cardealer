package softuni.cardealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.cardealer.models.entities.Car;
import softuni.cardealer.models.entities.Part;
import softuni.cardealer.models.entities.dtos.*;
import softuni.cardealer.repositories.CarRepository;
import softuni.cardealer.services.CarService;
import softuni.cardealer.services.PartService;
import softuni.cardealer.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final PartService partService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final Random random;

    @Autowired
    public CarServiceImpl(PartService partService, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository, Random random) {
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.random = random;
    }

    @Override
    public void seedCars(List<CarSeedDto> carSeedDtos) {
        carSeedDtos.forEach(carSeedDto -> {
            if (this.validationUtil.isValid(carSeedDto)) {
                if (this.isCarExist(carSeedDto.getMake(), carSeedDto.getModel())) {
                    System.out.println("Car already in DB");
                } else {
                    Car car = this.modelMapper.map(carSeedDto, Car.class);
                    car.setParts(this.partService.getRandomParts());

                    System.out.println();

                    this.carRepository.saveAndFlush(car);
                }
            } else {
                this.validationUtil.violations(carSeedDto).forEach(v -> System.out.println(v.getMessage()));
            }
        });
    }

    @Override
    public Car getRandomCar() {
        long randomId = this.random.nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.findById(randomId).orElse(null);
    }

    @Override
    public CarViewRootDto writeCarsByMakeToyota() {
        CarViewRootDto carViewRootDto = new CarViewRootDto();
        List<Car> cars = this.carRepository.getAllByMakeToyota("Toyota");
        List<CarViewDto> carViewDtos = new ArrayList<>();
        cars.forEach(car -> {
            CarViewDto carViewDto = this.modelMapper.map(car, CarViewDto.class);
            carViewDto.setDistanceTravelled(car.getDistance());
            carViewDtos.add(carViewDto);
        });
        carViewRootDto.setCars(carViewDtos);
        return carViewRootDto;
    }

    @Override
    public CarViewRootDto getAllCarsAndParts() {
        CarViewRootDto carViewRootDto = new CarViewRootDto();

        carViewRootDto.setCars(this.setListOfCars());
        return carViewRootDto;
    }

    private List<CarViewDto> setListOfCars() {

        List<CarViewDto> carViewDtos = new ArrayList<>();
        List<Car> cars = this.carRepository.findAll();
        for (Car car : cars) {
            CarViewDto carViewDto = this.modelMapper.map(car, CarViewDto.class);
            carViewDto.setDistanceTravelled(car.getDistance());
            carViewDto.setParts(this.setCarParts(car));
            carViewDtos.add(carViewDto);
        }
        return carViewDtos;
    }

    private PartViewRootDto setCarParts(Car car) {
        PartViewRootDto partViewRootDto = new PartViewRootDto();
        List<Part>parts = car.getParts();
        List<PartViewDto>partViewDtos = new ArrayList<>();
        for (Part part : parts) {
            PartViewDto partViewDto = this.modelMapper.map(part, PartViewDto.class);
            partViewDto.setPrice(part.getPrice());
            partViewDtos.add(partViewDto);
        }
        partViewRootDto.setParts(partViewDtos);
        return partViewRootDto;
    }


    private boolean isCarExist(String make, String model) {
        return this.carRepository.findByMakeAndModel(make, model) != null;
    }
}
