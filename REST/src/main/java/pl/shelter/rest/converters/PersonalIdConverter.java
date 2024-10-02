package pl.shelter.rest.converters;


import pl.shelter.dto.accounts.PersonalIdDto;
import pl.shelter.rest.model.accounts.PersonalId;

public class PersonalIdConverter {

    public static PersonalIdDto toDto(PersonalId personalId) {
        return new PersonalIdDto(personalId.getId());
    }

    public static PersonalId fromDto(PersonalIdDto personalIdDto) {
        return (null == personalIdDto ? null:PersonalId.valueOf(personalIdDto.getId()));
    }
}
