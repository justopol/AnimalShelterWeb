package pl.shelter.dto.accounts;

import pl.shelter.dto.AbstractDto;

import java.util.UUID;

public class AccountDto extends  AbstractDto{

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String personId;

    public AccountDto() {
    }

    public AccountDto(UUID id, long version, String firstName, String lastName, String email, String personId) {
        super(id, version);
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.personId = personId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
