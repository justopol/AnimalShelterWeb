package pl.shelter.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import pl.shelter.dto.ValidationMessages;

public class PersonalIdDto {

    @NotNull
    @Size(min = 11, max = 11, message = ValidationMessages.Account.PERSONID_LENGTH)
    @Positive(message = ValidationMessages.Account.PERSONID_FORMAT)
    private String id;

    @JsonbCreator
    public PersonalIdDto(@JsonbProperty("id") String personalId) {
        this.id = personalId;
    }

    public PersonalIdDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonalIdDTO{" +
                "id='" + id + '\'' +
                '}';
    }
}
