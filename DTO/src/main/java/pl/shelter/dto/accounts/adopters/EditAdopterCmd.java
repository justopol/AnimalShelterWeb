package pl.shelter.dto.accounts.adopters;

import pl.shelter.dto.accounts.EditAccountCmd;

public class EditAdopterCmd extends EditAccountCmd {
    private String streetName;
    private String streetNumber;
    private String city;


    public EditAdopterCmd() {
    }

    public EditAdopterCmd(long originalVersion, String firstName, String lastName, String email, String streetName, String streetNumber, String city) {
        super(originalVersion, firstName, lastName, email);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
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
