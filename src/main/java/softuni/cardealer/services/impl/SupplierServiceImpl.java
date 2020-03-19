package softuni.cardealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.models.entities.Supplier;
import softuni.cardealer.models.entities.dtos.SupplierSeedDto;
import softuni.cardealer.models.entities.dtos.SupplierViewDto;
import softuni.cardealer.models.entities.dtos.SupplierViewRootDto;
import softuni.cardealer.repositories.SupplierRepository;
import softuni.cardealer.services.SupplierService;
import softuni.cardealer.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.random = random;
    }

    @Override
    public void seedSuppliers(List<SupplierSeedDto> supplierSeedDtos) {
        supplierSeedDtos.stream().forEach(supplierSeedDto -> {
            if(this.validationUtil.isValid(supplierSeedDto)){
                if(this.supplierRepository.findByName(supplierSeedDto.getName())==null){
                    Supplier supplier = this.modelMapper.map(supplierSeedDto,Supplier.class);
                    supplier.setUsesImportedParts(supplierSeedDto.isImporter());
                    System.out.println();
                    this.supplierRepository.saveAndFlush(supplier);
                }else {
                    System.out.println("Already in DB");
                }
            }else {
                this.validationUtil.violations(supplierSeedDto)
                        .forEach(s-> System.out.println(s.getMessage()));
            }
        });
    }

    @Override
    public Supplier getRandomSupplier() {
        long randomID = random.nextInt((int) supplierRepository.count())+1;
        return supplierRepository.findById(randomID).orElse(null);
    }

    @Override
    public SupplierViewRootDto getLocalSuppliers() {
        SupplierViewRootDto supplierViewRootDto = new SupplierViewRootDto();
        List<Supplier> suppliers = this.supplierRepository.findAllByUsesNotImportedParts();
        List<SupplierViewDto>supplierViewDtos = new ArrayList<>();

        suppliers.forEach(supplier -> {
            SupplierViewDto supplierViewDto = this.modelMapper.map(supplier,SupplierViewDto.class);
            supplierViewDto.setPartsCount(supplier.getParts().size());
            supplierViewDtos.add(supplierViewDto);
        });
        supplierViewRootDto.setSuppliers(supplierViewDtos);
        return supplierViewRootDto;
    }
}
