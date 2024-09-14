package pl.shelter.dto.animals;

public class EditReptileCmd extends AddAnimalCmd{
    private long originalVersion;


    public EditReptileCmd() {
    }

    public EditReptileCmd(String type, int age, String name, long originalVersion) {
        super(type, age, name);
        this.originalVersion = originalVersion;
    }

    public long getOriginalVersion() {
        return originalVersion;
    }

    public void setOriginalVersion(long originalVersion) {
        this.originalVersion = originalVersion;
    }
}
