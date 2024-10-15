package pl.shelter.dto.animals;

import pl.shelter.dto.enums.Castrated;

public class EditAnimalCmd extends AddAnimalCmd{
    public EditAnimalCmd() {
    }

    private long originalVersion;
    private Castrated castrated;
    public EditAnimalCmd(long originalVersion, String type, int age, String name, String castrated) {
        super(type, age, name);
        this.originalVersion = originalVersion;
        this.castrated = Castrated.valueOf(castrated);
    }

    public Castrated getCastrated() {
        return castrated;
    }

    public void setCastrated(Castrated castrated) {
        this.castrated = castrated;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }
    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }
}
