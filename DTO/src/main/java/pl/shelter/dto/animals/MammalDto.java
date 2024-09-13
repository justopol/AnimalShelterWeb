package pl.shelter.dto.animals;

import java.util.UUID;

public class MammalDto extends AnimalDto{
    private boolean castrated;

    public MammalDto() {
    }

    public MammalDto(UUID id, long version, String type, boolean available, String name, int age, double adoptionPrice, String adoptionStatus, boolean castrated) {
        super(id, version, type, available, name, age, adoptionPrice, adoptionStatus);
        this.castrated = castrated;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }
}
