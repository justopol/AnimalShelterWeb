package pl.shelter.rest.model.adopters;

import jakarta.persistence.*;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.PersonalId;
import pl.shelter.rest.model.enums.AdopterType;

import java.util.UUID;

@Entity
@DiscriminatorValue("ADOPTER")
@SecondaryTable(name = "adopter")
@NamedQueries({
        @NamedQuery(name = "Adopter.findByLogin", query = "select a from Adopter a join Account c on a.id = c.id where c.login = :login"),
})
public class Adopter extends Account {
    @Enumerated(EnumType.STRING)
    @Column(table = "adopter")
    private AdopterType adopterType;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(table = "adopter", updatable = false, nullable = false)
    private Address address;

    public Adopter() {
    }

    public Adopter(String login, String password, String email, String firstName, String lastName, PersonalId personId,Address address, AdopterType adopterType) {
        super(login, password, email, firstName, lastName, personId);
        this.address= address;
        this.adopterType = adopterType;
    }

    public Adopter(String firstName, String lastName, String email, Address address) {
        this.firstName =firstName;
        this.lastName = lastName;
        this.setEmail(email);
        this.address = address;
    }

    @Transient
    public int getMaxAnimals() {
        return 5;
    }

    public AdopterType getAdopterType() {
        return adopterType;
    }

    public void setAdopterType(AdopterType adopterType) {
        this.adopterType = adopterType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDiscount() {
        return switch (adopterType) {
            case STANDARD, BLACKLISTED -> 0.0;
            case PREVIOUS_ADOPTER -> 0.5;
        };
    }
public String getFullName(){
        return this.firstName+" "+this.lastName;
}


}
