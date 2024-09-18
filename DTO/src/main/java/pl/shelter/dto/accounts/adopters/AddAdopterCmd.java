package pl.shelter.dto.accounts.adopters;

import pl.shelter.dto.accounts.AddAccountCmd;

public class AddAdopterCmd extends AddAccountCmd {

    private String streetName;
    private String streetNumber;
    private String city;

    public AddAdopterCmd() {
    }

    public AddAdopterCmd(String login, String password, String email, String firstName, String lastName, String personalId, String streetName, String streetNumber, String city) {
        super(login, password, email, firstName, lastName, personalId);
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
