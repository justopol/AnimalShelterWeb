package pl.shelter.dto.accounts.adoptions;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AddAdoptionCmd {

    @NotNull
    private UUID animalUuid;
    @NotNull
    private UUID adopterUuid;
    public AddAdoptionCmd() {
    }

    @JsonbCreator
    public AddAdoptionCmd(@JsonbProperty("animalId") UUID animalUuid, @JsonbProperty("adopterId") UUID adopterUuid) {
        this.animalUuid = animalUuid;
        this.adopterUuid = adopterUuid;
    }

    public UUID getAnimalUuid() {
        return animalUuid;
    }

    public void setAnimalUuid(UUID animalUuid) {
        this.animalUuid = animalUuid;
    }

    public UUID getAdopterUuid() {
        return adopterUuid;
    }

    public void setAdopterUuid(UUID adopterUuid) {
        this.adopterUuid = adopterUuid;
    }
}
