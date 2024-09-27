package pl.shelter.dto.accounts.adoptions;

import java.util.UUID;

public class AddAdoptionCmd {

    private UUID animalUuid;
    private UUID adopterUuid;
    public AddAdoptionCmd() {
    }

    public AddAdoptionCmd(UUID animalUuid, UUID adopterUuid) {
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
