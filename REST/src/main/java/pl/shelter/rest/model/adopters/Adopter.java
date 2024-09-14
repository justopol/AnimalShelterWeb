package pl.shelter.rest.model.adopters;

import jakarta.persistence.*;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.PersonalId;

import java.util.UUID;

@Entity
public class Adopter extends Account {
    private AdopterType adopterType;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private Address address;

    public Adopter() {
    }

    public Adopter(String login, String password, String email, String firstName, String lastName, PersonalId personId,Address address, AdopterType adopterType) {
        super(login, password, email, firstName, lastName, personId);
        this.address= address;
        this.adopterType = adopterType;
    }

    public AdopterType getAdopterType() {
        return adopterType;
    }

    public double getDiscount() {
        return switch (adopterType) {
            case STANDARD, BLACKLISTED -> 0.0;
            case PREVIOUS_ADOPTER -> 0.5;
        };

    }



}
