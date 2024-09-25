package pl.shelter.rest.model.adoptions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import pl.shelter.rest.exceptions.entities.AdoptionException;
import pl.shelter.rest.model.AbstractEntity;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.enums.AdoptionStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Adoption extends AbstractEntity {

    private LocalDate startAdoptionTime;
    private LocalDate endAdoptionTime;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private Adopter adopter;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private Animal animal;
    private double finalAdoptionCost;

    public Adoption() {
        super(UUID.randomUUID());
    }

    public void createAdoption(LocalDate startAdoptionTime, Adopter adopter, Animal animal) throws AdoptionException {
        if (animal ==null){
            throw new AdoptionException(AdoptionException.ANIMAL_NOT_EXISTS);
        }
        this.startAdoptionTime = startAdoptionTime;
        this.adopter = adopter;
        if (animal.isReadyForAdoption()){//to do
        }
        this.animal = animal;
        animal.setAdoptionStatus(AdoptionStatus.UNDER_ADOPTION);
        this.finalAdoptionCost = animal.getAdoptionPrice() * (1 - adopter.getDiscount()) * animal.getBloodnessMultiplier();
    }
    public void finishAdoption(LocalDate endAdoptionTime) throws AdoptionException {
        if (animal ==null){
            throw new AdoptionException(AdoptionException.ANIMAL_NOT_EXISTS);
        }
        this.animal.setAdoptionStatus(AdoptionStatus.ADOPTED);
        if (endAdoptionTime.isBefore(startAdoptionTime)){
            throw new AdoptionException(AdoptionException.TIME_EXCEPTION);
        }
        this.endAdoptionTime = endAdoptionTime;
    }
    public long calculateDays() throws AdoptionException {
        if (endAdoptionTime.isBefore(startAdoptionTime)){
            throw new AdoptionException(AdoptionException.TIME_EXCEPTION);
        }
        return ChronoUnit.DAYS.between(startAdoptionTime,endAdoptionTime);
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public Animal getAnimal() {
        return animal;
    }

    public double getFinalAdoptionCost() {
        return finalAdoptionCost;
    }

}
