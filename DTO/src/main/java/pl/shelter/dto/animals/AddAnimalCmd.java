package pl.shelter.dto.animals;

public abstract class AddAnimalCmd {
    public AddAnimalCmd() {
    }

    private String type;
    private int age;
    private String name;

    public AddAnimalCmd(String type, int age, String name) {
        this.type = type;
        this.age = age;
        this.name = name;
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
}
