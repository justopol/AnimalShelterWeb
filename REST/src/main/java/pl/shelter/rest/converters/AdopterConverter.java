package pl.shelter.rest.converters;

import org.jetbrains.annotations.NotNull;
import pl.shelter.dto.adopters.AdopterDto;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdopterConverter {
    public static AdopterDto toDto(Adopter adopter){
        return new AdopterDto(adopter.getId(),
                adopter.getVersion(),
                adopter.getPersonId().toDtoString(),
                adopter.getFirstName(),
                adopter.getLastName(),
                adopter.getAddress().toDtoString(),
                adopter.getAdopterType().name());
    }
    public static List<AdopterDto> toDto(List<Adopter> adopters) {
        return null == adopters ? null : adopters.stream()
                .filter(Objects::nonNull)
                .map(AdopterConverter::toDto)
                .collect(Collectors.toList());
    }
}
