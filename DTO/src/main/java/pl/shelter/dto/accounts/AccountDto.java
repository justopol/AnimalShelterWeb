package pl.shelter.dto.accounts;

import pl.shelter.dto.AbstractDto;

import java.util.UUID;

public class AccountDto extends AbstractDto {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String role;
    protected boolean active;

    protected String personId;
    protected UUID accountId;
    protected long accountVersion;

    public AccountDto() {
    }

    public AccountDto(UUID id, long version, String role, String login, boolean active, String firstName, String lastName, String email, String personId) {
        super(id, version);
        this.accountId=id;
        this.accountVersion=version;
        this.role = role;
        this.login = login;
        this.active = active;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.personId = personId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public long getAccountVersion() {
        return accountVersion;
    }

    public void setAccountVersion(long accountVersion) {
        this.accountVersion = accountVersion;
    }
}
