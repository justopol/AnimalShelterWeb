package pl.shelter.rest.converters;

import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.dto.animals.EditMammalCmd;
import pl.shelter.dto.animals.MammalDto;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;

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
    public static List<MammalDto> toDto(List<Animal> mammals) {
        return (null == mammals ? null : mammals.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto((Mammal) element))
                .collect(Collectors.toList())
        );
    }
}
