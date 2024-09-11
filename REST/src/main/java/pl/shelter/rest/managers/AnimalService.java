package pl.shelter.rest.managers;

import pl.shelter.rest.model.animals.Animal;

import java.util.List;

public interface AnimalService {

    void createAnimal(Animal animal);
    List<Animal> getAnimals();
}
