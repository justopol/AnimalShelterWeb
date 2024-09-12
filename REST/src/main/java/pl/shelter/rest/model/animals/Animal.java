package pl.shelter.rest.model.animals;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import pl.shelter.rest.model.AbstractEntity;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal extends AbstractEntity {
    private String type;


    private boolean available;
    private double basicPrice = 20;

    private  String name;
    private int age;

    protected AdoptionStatus adoptionStatus = AdoptionStatus.FOR_ADOPTION;


    public Animal() {
        super(UUID.randomUUID());
    }
    public Animal(String type, int age, String name) {
        super(UUID.randomUUID());
        this.type = type;
        this.age = age;
        this.name = name;
    }

    protected abstract Bloodness getBloodness();

    public abstract boolean isReadyForAdoption();
    public AdoptionStatus getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(AdoptionStatus adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBloodnessMultiplier() {
        return switch (getBloodness()) {
            case WARM -> 1;
            case COLD -> 2;
        };
    }

    public double getAdoptionPrice() {
        return basicPrice * getBloodnessMultiplier();
    }


//    public void addAdoption(Adoption adoption) {
//        this.currentAdoption = adoption;
//    }
//
//    public void removeAdoption() {
//        this.currentAdoption = null;
//    }
//
//    public Adoption getCurrentAdoption() {
//        return currentAdoption;
//    }
}
