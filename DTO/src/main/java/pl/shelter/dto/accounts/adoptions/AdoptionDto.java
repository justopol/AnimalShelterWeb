package pl.shelter.dto.accounts.adoptions;

import pl.shelter.dto.AbstractDto;

import java.util.UUID;

public class AdoptionDto extends AbstractDto {

    private int adoptionDurationDays;
    private UUID adopterId;
    private String adopterFullName;
    private  String adopterType;
    private UUID animalId;
    private  String animalType;
    private String animalName;
    private double finalAdoptionCost;

    public AdoptionDto() {
    }

    public AdoptionDto(UUID id, long version, int adoptionDurationDays, UUID adopterId, String adopterFullName,
                       String adopterType, UUID animalId, String animalType, String animalName, double finalAdoptionCost) {
        super(id, version);
        this.adoptionDurationDays = adoptionDurationDays;
        this.adopterId = adopterId;
        this.adopterFullName = adopterFullName;
        this.adopterType = adopterType;
        this.animalId = animalId;
        this.animalType = animalType;
        this.animalName = animalName;
        this.finalAdoptionCost = finalAdoptionCost;
    }

    public int getAdoptionDurationDays() {
        return adoptionDurationDays;
    }

    public void setAdoptionDurationDays(int adoptionDurationDays) {
        this.adoptionDurationDays = adoptionDurationDays;
    }

    public UUID getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(UUID adopterId) {
        this.adopterId = adopterId;
    }

    public String getAdopterFullName() {
        return adopterFullName;
    }

    public void setAdopterFullName(String adopterFullName) {
        this.adopterFullName = adopterFullName;
    }

    public String getAdopterType() {
        return adopterType;
    }

    public void setAdopterType(String adopterType) {
        this.adopterType = adopterType;
    }

    public UUID getAnimalId() {
        return animalId;
    }

    public void setAnimalId(UUID animalId) {
        this.animalId = animalId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public double getFinalAdoptionCost() {
        return finalAdoptionCost;
    }

    public void setFinalAdoptionCost(double finalAdoptionCost) {
        this.finalAdoptionCost = finalAdoptionCost;
    }
}