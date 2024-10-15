package pl.shelter.dto.animals;


public class EditAnimalCmd extends AddAnimalCmd{
    public EditAnimalCmd() {
    }

    private long originalVersion;
    private boolean castrated;
    public EditAnimalCmd(long originalVersion, String type, int age, String name, boolean castrated) {
        super(type, age, name);
        this.originalVersion = originalVersion;
        this.castrated = castrated;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }
    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }
}
