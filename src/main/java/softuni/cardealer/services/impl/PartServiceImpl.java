package softuni.cardealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.cardealer.models.entities.Part;
import softuni.cardealer.models.entities.dtos.PartSeedDto;
import softuni.cardealer.repositories.PartRepository;
import softuni.cardealer.repositories.SupplierRepository;
import softuni.cardealer.services.PartService;
import softuni.cardealer.services.SupplierService;
import softuni.cardealer.utils.ValidationUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;
    private final Random random;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, ValidationUtil validationUtil, SupplierService supplierService, SupplierRepository supplierRepository, SupplierService supplierService1, Random random) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService1;
        this.random = random;
    }

    @Override
    public void seedParts(List<PartSeedDto> partSeedDtos) {
        partSeedDtos.forEach(partSeedDto -> {
            if(this.validationUtil.isValid(partSeedDto)){
                if(this.isPartExist(partSeedDto.getName(),partSeedDto.getPrice())){
                    System.out.println("Part existis in DB");
                }else {
                    Part part = this.modelMapper.map(partSeedDto, Part.class);
                    part.setSupplier(this.supplierService.getRandomSupplier());

                    this.partRepository.saveAndFlush(part);
                }
            }else {
                this.validationUtil.violations(partSeedDto).forEach(v-> System.out.println(v.getMessage()));
            }
        });
    }

    @Override
    public List<Part> getRandomParts() {
        int randomCount = this.random.nextInt(10)+10;
        List<Part> randomParts = new ArrayList<>();
        for (int i = 0; i <randomCount ; i++) {
        long randomId = this.random.nextInt((int) this.partRepository.count())+1;
            randomParts.add(this.partRepository.findById(randomId).orElse(null));
        }
        return randomParts;
    }

    private boolean isPartExist(String name, BigDecimal price) {
        return this.partRepository.findByNameAndPrice(name, price) != null;
    }
}
