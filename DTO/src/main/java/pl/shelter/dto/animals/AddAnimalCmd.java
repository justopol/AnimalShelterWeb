package pl.shelter.dto.animals;

public class AddAnimalCmd {
    public AddAnimalCmd() {
    }

    private String type;
    private int age;
    private String name;
    private boolean castrated;

    public AddAnimalCmd(String type, int age, String name, boolean castrated) {
        this.type = type;
        this.age = age;
        this.name = name;
        this.castrated=castrated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }
}
