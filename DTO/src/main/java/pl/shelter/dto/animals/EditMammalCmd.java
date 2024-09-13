package pl.shelter.dto.animals;

public class EditMammalCmd extends AddAnimalCmd{
    public EditMammalCmd() {
    }

    private long originalVersion;
    private boolean isCastrated;
    public EditMammalCmd(long originalVersion, String type, int age, String name, boolean isCastrated) {
        super(type, age, name);
        this.originalVersion = originalVersion;
        this.isCastrated = isCastrated;
    }
    public boolean isCastrated() {
        return isCastrated;
    }

    public void setCastrated(boolean castrated) {
        isCastrated = castrated;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }
    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }
}
