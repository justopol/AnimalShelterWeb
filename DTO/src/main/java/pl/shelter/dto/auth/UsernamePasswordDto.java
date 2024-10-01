package pl.shelter.dto.auth;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import pl.shelter.dto.ValidationMessages;

//not_tested
public class UsernamePasswordDto {
    @NotBlank(message = ValidationMessages.Auth.LOGIN_BLANK)
    private String username;
    @NotBlank(message = ValidationMessages.Auth.PASSWORD_BLANK)
    private String password;

    @JsonbCreator
    public UsernamePasswordDto(@JsonbProperty("username") String username,
                               @JsonbProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
