package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.repositories.AnimalFacade;

public class AnimalManager implements AnimalService{
    @Inject
    private AnimalFacade animalFacade;
    @Override
    public void createAnimal(Animal animal) {
        animalFacade.create(animal);
    }
}
