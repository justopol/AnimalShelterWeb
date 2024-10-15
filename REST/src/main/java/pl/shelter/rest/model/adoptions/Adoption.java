package pl.shelter.rest.model.adoptions;

import jakarta.persistence.*;
import pl.shelter.rest.exceptions.AdopterException;
import pl.shelter.rest.exceptions.AdoptionException;
import pl.shelter.rest.model.AbstractEntity;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.enums.AdopterType;
import pl.shelter.rest.model.enums.AdoptionStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity

@NamedQueries({
        @NamedQuery(name = "Adoption.findByLogin", query = "select a from Adoption a join Adopter d on a.adopter.id = d.id join Account c on d.id = c.id where c.login = :login"),
})
public class Adoption extends AbstractEntity {

    private LocalDate startAdoptionTime;
    private LocalDate endAdoptionTime;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Adopter adopter;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Animal animal;
    private double finalAdoptionCost;

    public Adoption() {
        super(UUID.randomUUID());
    }

    public void createAdoption(LocalDate startAdoptionTime, Adopter adopter, Animal animal) throws AdoptionException {
        if (animal == null) {
            throw AdoptionException.createForAnimalNotExist();
        }
        if (animal.getAdoptionStatus()==AdoptionStatus.UNDER_ADOPTION || animal.getAdoptionStatus()==AdoptionStatus.ADOPTED){
            throw AdoptionException.createForAnimalAdopted();
        }
        this.startAdoptionTime = startAdoptionTime;
        if (adopter.getAdopterType()== AdopterType.BLACKLISTED){
            throw AdopterException.createForBlacklistAdopter();
        }
        this.adopter = adopter;
        if (!animal.isReadyForAdoption()) {
            throw AdoptionException.createForNotReadyForAdoption();
        }
        this.animal = animal;
        animal.setAdoptionStatus(AdoptionStatus.UNDER_ADOPTION);
        this.finalAdoptionCost = animal.getAdoptionPrice() * (1 - adopter.getDiscount()) * animal.getBloodnessMultiplier();
    }

    public void finishAdoption(LocalDate endAdoptionTime) throws AdoptionException {
        if (animal == null) {
            throw AdoptionException.createForAnimalNotExist();
        }
        if (animal.getAdoptionStatus()==AdoptionStatus.ADOPTED){
            throw AdoptionException.createForAnimalAdopted();
        }
        if (adopter.getAdopterType()== AdopterType.BLACKLISTED){
            throw AdopterException.createForBlacklistAdopter();
        }
        this.animal.setAdoptionStatus(AdoptionStatus.ADOPTED);
        this.adopter.setAdopterType(AdopterType.PREVIOUS_ADOPTER);
        if (endAdoptionTime.isBefore(startAdoptionTime)) {
            throw AdoptionException.createForTimeException();
        }
        this.endAdoptionTime = endAdoptionTime;
    }
    public void cancelAdoption() throws AdoptionException {
        if (animal == null) {
            throw AdoptionException.createForAnimalNotExist();
        }
        this.animal.setAdoptionStatus(AdoptionStatus.FOR_ADOPTION);
        this.adopter.setAdopterType(AdopterType.BLACKLISTED);
    }


    public int calculateDays() {
        if (endAdoptionTime == null) {
            return (int) ChronoUnit.DAYS.between(startAdoptionTime, LocalDate.now());
        }
        return (int) ChronoUnit.DAYS.between(startAdoptionTime, endAdoptionTime);
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

    public LocalDate getStartAdoptionTime() {
        return startAdoptionTime;
    }
}
