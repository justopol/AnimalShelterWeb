package pl.shelter.rest.managers;

import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    void createAnimal(Animal animal);
    List<Animal> getAnimals();
    void editMammalById(UUID id, long originalVersion, Mammal mammalModifications);
    Mammal findMammalById(UUID id);
    Reptile findReptileById(UUID id);
    void editReptileById(UUID id, long originalVersion, Reptile reptileModifications);
}
