package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.repositories.AnimalFacade;

import java.util.List;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AnimalManager implements AnimalService{
    @Inject
    private AnimalFacade animalFacade;
    @Override
    public void createAnimal(Animal animal) {
        animalFacade.create(animal);
    }

    @Override
    public List<Animal> getAnimals() {
        return animalFacade.getAnimals();
    }
}
