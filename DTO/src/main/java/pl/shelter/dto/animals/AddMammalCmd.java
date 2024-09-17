package pl.shelter.dto.animals;

public class AddMammalCmd extends AddAnimalCmd {

    private boolean isCastrated;
    public AddMammalCmd() {
    }

    public AddMammalCmd(String type, int age, String name, boolean isCastrated) {
        super(type, age, name);
        this.isCastrated = isCastrated;
    }

    public boolean isCastrated() {
        return isCastrated;
    }

    public void setCastrated(boolean castrated) {
        isCastrated = castrated;
    }
}
