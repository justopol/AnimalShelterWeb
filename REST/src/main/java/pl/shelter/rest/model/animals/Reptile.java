package pl.shelter.rest.model.animals;

import jakarta.persistence.Entity;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

@Entity
public class Reptile extends Animal{
    public Reptile() {
    }

    public Reptile(String type, int age, String name) {
        super(type, age, name);
    }

    @Override
    protected Bloodness getBloodness() {
        return Bloodness.COLD;
    }

    @Override
    public boolean isReadyForAdoption() {
        if (adoptionStatus.equals(AdoptionStatus.FOR_ADOPTION)){
            return true;
        }
        return false;
    }
}
