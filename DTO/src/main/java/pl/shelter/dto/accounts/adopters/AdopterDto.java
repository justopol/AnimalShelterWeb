package pl.shelter.dto.accounts.adopters;

import pl.shelter.dto.accounts.AccountDto;

import java.util.UUID;

public class AdopterDto extends AccountDto {
    protected String address;
    protected String adopterType;
    private String adopterId;
    private String streetName;
    private String streetNumber;
    private String city;

    public AdopterDto() {
    }

    public AdopterDto(UUID id, long version, String role, String login, boolean active, String firstName, String lastName, String email, String personId, String address, String adopterType, String streetName, String streetNumber, String city) {
        super(id, version, role, login, active, firstName, lastName, email, personId);
        this.adopterId = id.toString();
        this.address = address;
        this.adopterType = adopterType;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
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

    public String getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
