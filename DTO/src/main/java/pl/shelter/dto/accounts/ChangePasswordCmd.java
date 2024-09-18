package pl.shelter.dto.accounts;

public class ChangePasswordCmd {

    private long originalVersion;
    private String password;

    public ChangePasswordCmd() {
    }

    public ChangePasswordCmd(long originalVersion, String password) {
        this.originalVersion = originalVersion;
        this.password = password;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }

    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
