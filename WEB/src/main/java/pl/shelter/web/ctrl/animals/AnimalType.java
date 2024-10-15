package pl.shelter.web.ctrl.animals;

import java.util.*;

public class AnimalType {
    private String group;
    private String type;

    public AnimalType(String group, String type) {
        this.group = group;
        this.type = type;
    }

    public static Map<String, String> getAnimalTypes() {
        var map = new HashMap<String, String>();

        map.put("lizard", "reptile");
        map.put("snake", "reptile");
        map.put("dog", "mammal");
        map.put("cat", "mammal");
        return map;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
