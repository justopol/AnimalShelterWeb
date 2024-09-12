package pl.shelter.rest.model.accounts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account {

    public Admin() {
    }

    public Admin(String login, String password, String email, String firstName, String lastName, PersonalId personId) {
        super(login, password, email, firstName, lastName, personId);
    }
}
