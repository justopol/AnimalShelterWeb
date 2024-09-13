package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.repositories.AnimalFacade;

import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AnimalManager implements AnimalService {
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

    @Override
    public void editMammalById(UUID id, long originalVersion, Mammal mammalModifications) {
        Mammal modificateMammal = findMammalById(id);
        editMammal(originalVersion, mammalModifications, modificateMammal);
    }

    @Override
    public Mammal findMammalById(UUID id) {
        return (Mammal)animalFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    private void editMammal(long originalVersion, Mammal mammalModifications, Mammal modifiedMammal) {
        if (originalVersion != modifiedMammal.getVersion())
            throw AppBaseException.createForOptimisticLock();

        if (null != mammalModifications.getType()) {
            modifiedMammal.setType(mammalModifications.getType());
        }

        modifiedMammal.setAge(mammalModifications.getAge());

        if (null != mammalModifications.getName()) {
            modifiedMammal.setName(mammalModifications.getName());
        }
        modifiedMammal.setCastrated(mammalModifications.isCastrated());
        animalFacade.edit(modifiedMammal);
    }


}
