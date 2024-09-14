package pl.shelter.rest.converters;

import pl.shelter.dto.animals.*;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnimalConverter {
    public static Animal fromAddMammalCmd(AddMammalCmd addMammalCmd) {
        return new Mammal(addMammalCmd.getType(),
                addMammalCmd.getAge(),
                addMammalCmd.getName(),
                addMammalCmd.isCastrated());
    }
    public static Mammal fromEditMammalCmd(EditMammalCmd editMammalCmd) {
        return new Mammal(editMammalCmd.getType(),
                editMammalCmd.getAge(),
                editMammalCmd.getName(),
                editMammalCmd.isCastrated());
    }
    public static MammalDto toDto(Mammal mammal){
        return new MammalDto(mammal.getId(),
                mammal.getVersion(),
                mammal.getType(),
                mammal.isReadyForAdoption(),
                mammal.getName(),
                mammal.getAge(),
                mammal.getAdoptionPrice(),
                mammal.getAdoptionStatus().name(),
                mammal.isCastrated());
    }
    public static ReptileDto toDto(Reptile reptile){
        return new ReptileDto(reptile.getId(),
                reptile.getVersion(),
                reptile.getType(),
                reptile.isReadyForAdoption(),
                reptile.getName(),
                reptile.getAge(),
                reptile.getAdoptionPrice(),
                reptile.getAdoptionStatus().name());
    }
    public static List<AnimalDto> toDto(List<Animal> animals) {
        return (null == animals ? null : animals.stream()
                .filter(Objects::nonNull)
                .map(animal -> {
                    //switch
                    if (animal instanceof Mammal){
                        return toDto((Mammal) animal);
                    }
                    else if(animal instanceof  Reptile){
                        return toDto((Reptile) animal);
                    }else {
                    throw new IllegalArgumentException("Unsupported animal type: " + animal.getClass());}
                })
                .collect(Collectors.toList())
        );
    }
    public static Animal fromAddReptileCmd(AddReptileCmd addReptileCmd) {
        return new Reptile(addReptileCmd.getType(),
                addReptileCmd.getAge(),
                addReptileCmd.getName());
    }

    public static Reptile fromEditReptileCmd(EditReptileCmd editReptileCmd) {
        return new Reptile(editReptileCmd.getType(),
                editReptileCmd.getAge(),
                editReptileCmd.getName());
    }

}
