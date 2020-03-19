package softuni.cardealer.services;

import softuni.cardealer.models.entities.Part;
import softuni.cardealer.models.entities.dtos.PartSeedDto;

import java.util.List;

public interface PartService {
    void seedParts(List<PartSeedDto>partSeedDtos);
    public List<Part> getRandomParts();
}
