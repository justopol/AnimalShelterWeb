package pl.shelter.rest.model.accounts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Account{
    public Employee() {
    }

    public Employee(String login, String password, String email, String firstName, String lastName, PersonalId personId) {
        super(login, password, email, firstName, lastName, personId);
    }
}

