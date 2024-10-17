package pl.shelter.rest.managers;

import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    void createAnimal(Animal animal);
    List<Animal> getAnimals();
    List<Animal> getForAdoptionAnimals();
    void editMammalById(UUID id, long originalVersion, Mammal mammalModifications);
    Animal findAnimalById(UUID id);
    void editReptileById(UUID id, long originalVersion, Reptile reptileModifications);
}
