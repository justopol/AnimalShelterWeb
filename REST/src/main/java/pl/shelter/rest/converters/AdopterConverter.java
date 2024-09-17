package pl.shelter.rest.converters;

import org.jetbrains.annotations.NotNull;
import pl.shelter.dto.adopters.AddAdopterCmd;
import pl.shelter.dto.adopters.AdopterDto;
import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.rest.model.accounts.PersonalId;
import pl.shelter.rest.model.adopters.Address;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adopters.AdopterType;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdopterConverter {
    public static AdopterDto toDto(Adopter adopter) {
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

    public static Adopter fromAddAdopterCmd(AddAdopterCmd addAdopterCmd) {
        return new Adopter(addAdopterCmd.getLogin(),
                "12345678",
                addAdopterCmd.getEmail(),
                addAdopterCmd.getFirstName(),
                addAdopterCmd.getLastName(),
                PersonalId.valueOf(addAdopterCmd.getPersonalId()),
                new Address(addAdopterCmd.getStreetName(),
                        addAdopterCmd.getStreetNumber(),
                        addAdopterCmd.getCity()),
                AdopterType.STANDARD);
    }
}
