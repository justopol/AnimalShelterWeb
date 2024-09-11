package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.repositories.AnimalFacade;

@TxTracked // To track transactions and method calls
@Transactional(Transactional.TxType.REQUIRES_NEW) // To manage application transaction boundaries
public class AnimalManager implements AnimalService{
    @Inject
    private AnimalFacade animalFacade;
    @Override
    public void createAnimal(Animal animal) {
        animalFacade.create(animal);
    }
}
