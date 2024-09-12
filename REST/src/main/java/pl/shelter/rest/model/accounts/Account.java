package pl.shelter.rest.model.accounts;

import jakarta.persistence.*;
import pl.shelter.rest.model.AbstractEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@NamedQueries({
        @NamedQuery(name = "Account.findByLogin", query = "select a from Account a where a.login = :login"),
        @NamedQuery(name = "Account.findByPersonId", query = "select a from Account a where a.personId.id = :id"),
        @NamedQuery(name = "Account.findByLoginAndPasswordAndActiveTrue", query = "select a from Account a where a.login = :login and a.password = :password and a.active = true")
})
@DiscriminatorValue("INVALID")
@SecondaryTable(name = "person")
public class Account extends AbstractEntity {
    public static final String DB_CONSTRAINT_UNIQUE_LOGIN = "account_login_key";
    public static final String DB_CONSTRAINT_UNIQUE_EMAIL = "person_email_key";
    public static final String DB_CONSTRAINT_UNIQUE_PERSONALID = "person_personalid_key";

    @Column(updatable = false)
    private String role;

    @Column(nullable = false, updatable = false, unique = true)
    private String login;
    private String password;

    @Column(table = "person", nullable = false, unique = true)
    private String email;
    private boolean active=true;

    @Column(table = "person")
    protected String firstName;
    @Column(table = "person")
    protected String lastName;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "personalid", table = "person", unique = true))
    protected PersonalId personId;

    public Account() {}

    public Account(String login, String password, String email, String firstName, String lastName, PersonalId personId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
    }

    public String getRole() {
        return role;
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

    public PersonalId getPersonId() {
        return personId;
    }

    public void setPersonId(PersonalId personId) {
        this.personId = personId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return super.toString() + " Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personId=" + personId +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
