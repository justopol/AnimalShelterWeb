package pl.shelter.rest.converters;

import pl.shelter.dto.accounts.EditEmployeeCmd;
import pl.shelter.dto.accounts.adopters.AddAdopterCmd;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adopters.EditAdopterCmd;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.accounts.PersonalId;
import pl.shelter.rest.model.adopters.Address;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adopters.AdopterType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdopterConverter {
    public static AdopterDto toDto(Adopter adopter) {
        return new AdopterDto(
                adopter.getId(),
                adopter.getVersion(),
                adopter.getFirstName(),
                adopter.getLastName(),
                adopter.getEmail(),
                adopter.getPersonId().toDtoString(),
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
                addAdopterCmd.getPassword(),
                addAdopterCmd.getEmail(),
                addAdopterCmd.getFirstName(),
                addAdopterCmd.getLastName(),
                PersonalId.valueOf(addAdopterCmd.getPersonalId()),
                new Address(addAdopterCmd.getStreetName(),
                        addAdopterCmd.getStreetNumber(),
                        addAdopterCmd.getCity()),
                AdopterType.STANDARD);
    }

    public static Adopter fromEditAdopterCmd(EditAdopterCmd editAdopterCmd) {
        return new Adopter(editAdopterCmd.getFirstName(),
                editAdopterCmd.getLastName(),
                editAdopterCmd.getEmail(),
                new Address(editAdopterCmd.getStreetName(),
                        editAdopterCmd.getStreetNumber(),
                        editAdopterCmd.getCity()));
    }
    public static AdopterType fromString(String adopterType){
        return AdopterType.valueOf(adopterType);
    }
}
