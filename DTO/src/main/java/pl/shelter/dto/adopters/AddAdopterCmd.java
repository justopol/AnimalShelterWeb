package pl.shelter.dto.adopters;

import pl.shelter.dto.AbstractDto;

import java.util.UUID;

public class AddAdopterCmd{

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String personalId;
    private String streetName;
    private String streetNumber;
    private String city;

    public AddAdopterCmd() {
    }

    public AddAdopterCmd(String login, String email, String firstName, String lastName,
                         String personalId, String streetName, String streetNumber, String city) {
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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
