package pl.shelter.dto.auth;

import jakarta.validation.constraints.NotBlank;
//not_tested
public class ChangePasswordDto {
    private String oldPassword;
    @NotBlank
    private String newPassword;

    public ChangePasswordDto() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
