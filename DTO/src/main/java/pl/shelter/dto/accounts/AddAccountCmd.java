package pl.shelter.dto.accounts;

import jakarta.validation.constraints.*;
import pl.shelter.dto.ValidationMessages;

public class AddAccountCmd {
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$", message = ValidationMessages.Account.LOGIN_FORMAT)
    @Size(min = 4, message = ValidationMessages.Account.LOGIN_LENGTH)
    private String login;
    @NotNull
    @Size(min = 6, message = ValidationMessages.Account.PASSWORD_LENGTH)
    private String password;
    @NotNull
    @Email(message = ValidationMessages.Account.EMAIL_FORMAT)
    private String email;
    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.FIRSTNAME_LENGTH)
    private String firstName;
    @NotNull
    @Size(min = 2,message = ValidationMessages.Account.LASTNAME_LENGTH)
    private String lastName;
    @NotNull
    @Size(min = 11, max = 11, message = ValidationMessages.Account.PERSONID_LENGTH)
    @Positive(message = ValidationMessages.Account.PERSONID_FORMAT)
    private String personalId;

    public AddAccountCmd() {
    }

    public AddAccountCmd(String login, String password, String email, String firstName, String lastName, String personalId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
