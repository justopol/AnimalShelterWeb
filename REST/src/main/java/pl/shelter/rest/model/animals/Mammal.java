package pl.shelter.rest.model.animals;

import jakarta.persistence.Entity;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

@Entity
public class Mammal extends Animal {
    public Mammal() {}

    public Mammal(String type, int age, String name, boolean castrated) {
        super(type, age, name);
        this.castrated = castrated;
    }

    @Override
    protected Bloodness getBloodness() {
        return Bloodness.WARM;
    }

    @Override
    public boolean isReadyForAdoption() {
        if (castrated && adoptionStatus.equals(AdoptionStatus.FOR_ADOPTION)) {
            return false;
        }
        return true;
    }

    private boolean castrated;

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }
}
