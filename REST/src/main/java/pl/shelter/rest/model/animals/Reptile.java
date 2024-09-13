package pl.shelter.rest.model.animals;

import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.model.enums.Bloodness;

public class Reptile extends Animal{
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
