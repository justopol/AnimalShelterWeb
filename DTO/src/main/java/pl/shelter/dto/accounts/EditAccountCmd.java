package pl.shelter.dto.accounts;

public class EditAccountCmd {

    private long originalVersion;
    private String firstName;
    private String lastName;
    private String email;

    public EditAccountCmd() {
    }

    public EditAccountCmd(long originalVersion, String firstName, String lastName, String email) {
        this.originalVersion = originalVersion;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }

    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
