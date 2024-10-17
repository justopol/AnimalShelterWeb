package pl.shelter.rest.model.animals;

import jakarta.persistence.*;
import pl.shelter.rest.model.AbstractEntity;
import pl.shelter.rest.model.adoptions.Adoption;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Animal.findForAdoption", query = "select a from Animal a where a.adoptionStatus = pl.shelter.rest.model.enums.AdoptionStatus.FOR_ADOPTION"),
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal extends AbstractEntity {
    private String type;
    private double basicPrice = 20;
    private String name;
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
}
