package pl.shelter.rest.model.animals;

import pl.shelter.rest.model.AbstractEntity;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

public class Mammal extends Animal {
    public Mammal() {}

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
