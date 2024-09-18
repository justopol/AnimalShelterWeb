package pl.shelter.dto.accounts.adopters;

import pl.shelter.dto.accounts.AccountDto;

import java.util.UUID;

public class AdopterDto extends AccountDto {
    protected String address;
    protected String adopterType;

    public AdopterDto() {
    }

    public AdopterDto(UUID id, long version, String firstName, String lastName, String email, String personId, String address, String adopterType) {
        super(id, version,firstName, lastName, email, personId);
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
}
