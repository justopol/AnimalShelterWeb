package pl.shelter.dto.accounts.adopters;

public class ChangeAdopterStatusCmd {
    private long originalVersion;
    private String adopterType;

    public ChangeAdopterStatusCmd(long originalVersion, String adopterType) {
        this.originalVersion = originalVersion;
        this.adopterType = adopterType;
    }

    public ChangeAdopterStatusCmd() {
    }

    public long getOriginalVersion() {
        return originalVersion;
    }

    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }

    public String getAdopterType() {
        return adopterType;
    }

    public void setAdopterType(String adopterType) {
        this.adopterType = adopterType;
    }
}
