package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.rest.exceptions.entities.AdoptionException;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdoptionConverter {
    public static AdoptionDto toDto(Adoption adoption) {
        return new AdoptionDto(
                adoption.getId(),
                adoption.getVersion(),
                adoption.calculateDays(),
                adoption.getAdopter().getId(),
                adoption.getAdopter().getFullName(),
                adoption.getAdopter().getAdopterType().name(),
                adoption.getAnimal().getId(),
                adoption.getAnimal().getType(),
                adoption.getAnimal().getName(),
                adoption.getFinalAdoptionCost()
               );
    }
    public static List<AdoptionDto> toDto(List<Adoption> adoptions) {
        return null == adoptions ? null : adoptions.stream()
                .filter(Objects::nonNull)
                .map(AdoptionConverter::toDto)
                .collect(Collectors.toList());
    }
}
