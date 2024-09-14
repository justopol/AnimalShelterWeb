package pl.shelter.dto.animals;

import java.util.UUID;

public class ReptileDto extends AnimalDto{
    public ReptileDto() {
    }

    public ReptileDto(UUID id, long version, String type, boolean available, String name, int age, double adoptionPrice, String adoptionStatus) {
        super(id, version, type, available, name, age, adoptionPrice, adoptionStatus);
    }
}
