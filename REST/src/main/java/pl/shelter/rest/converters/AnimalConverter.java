package pl.shelter.rest.converters;

import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;

public class AnimalConverter {
    public static Animal fromAddMammalCmd(AddMammalCmd addMammalCmd) {
        return new Mammal(addMammalCmd.getType(),
                addMammalCmd.getAge(),
                addMammalCmd.getName(),
                addMammalCmd.isCastrated());
    }
}
