package pl.shelter.rest.model.adopters;

import jakarta.persistence.Entity;
import pl.shelter.rest.model.AbstractEntity;

@Entity
public class Address extends AbstractEntity {
    private String streetName;
    private String streetNumber;
    private String city;

    public Address() {
    }

    public Address(final String streetName, final String streetNumber, final String city) {
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

    @Override
    public String toString() {
        return "Address{" + "streetName=" + streetName + ", streetNumber=" + streetNumber + ", city=" + city + '}';
    }
}
