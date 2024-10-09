package pl.shelter.dto.accounts.adopters;

import pl.shelter.dto.accounts.AccountDto;

import java.util.UUID;

public class AdopterDto extends AccountDto {
    protected String address;
    protected String adopterType;
    private String adopterId;

    public AdopterDto() {
    }

    public AdopterDto(UUID id, long version, String role, String login, boolean active, String firstName, String lastName, String email, String personId, String address, String adopterType) {
        super(id, version, role, login, active, firstName, lastName, email, personId);
        this.adopterId=id.toString();
        this.address = address;
        this.adopterType = adopterType;
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

    public String getAdopterId(){return adopterId;}

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }
}
