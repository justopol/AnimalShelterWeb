package pl.shelter.dto.adopters;

import pl.shelter.dto.AbstractDto;

import java.util.UUID;

public class AdopterDto extends AbstractDto {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String adopterType;
    protected String personId;

    public AdopterDto() {
    }

    public AdopterDto(UUID id, long version, String personId, String firstName, String lastName, String address, String adopterType) {
        super(id, version);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.adopterType = adopterType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdopterType() {
        return adopterType;
    }

    public void setAdopterType(String adopterType) {
        this.adopterType = adopterType;
    }
}
