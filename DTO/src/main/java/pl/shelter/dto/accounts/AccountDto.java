package pl.shelter.dto.accounts;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import pl.shelter.dto.AbstractDto;
import pl.shelter.dto.ValidationMessages;

import java.util.UUID;

public class AccountDto extends AbstractDto {

    private String role;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$", message = ValidationMessages.Account.LOGIN_FORMAT)
    @Size(min = 4, message = ValidationMessages.Account.LOGIN_LENGTH)
    private String login;
    private boolean active;

    @NotNull
    @Email(message = ValidationMessages.Account.EMAIL_FORMAT)
    private String email;
    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.FIRSTNAME_LENGTH)
    private String firstName;
    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.LASTNAME_LENGTH)
    private String lastName;
    @Valid
    protected PersonalIdDto personId;

    @JsonbCreator
    public AccountDto(@JsonbProperty("id") UUID id,
                      @JsonbProperty("version") long version,
                      @JsonbProperty("role") String role,
                      @JsonbProperty("login") String login,
                      @JsonbProperty("email") String email,
                      @JsonbProperty("active") boolean active,
                      @JsonbProperty("firstName") String firstName,
                      @JsonbProperty("lastName") String lastName,
                      @JsonbProperty("personId") PersonalIdDto personId) {
        super(id, version);
        this.role = role;
        this.login = login;
        this.email = email;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
    }

    public AccountDto() {
        super();
        this.personId = new PersonalIdDto();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PersonalIdDto getPersonId() {
        return personId;
    }

    public void setPersonId(PersonalIdDto personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return super.toString() + " AccountDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personId=" + personId +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
