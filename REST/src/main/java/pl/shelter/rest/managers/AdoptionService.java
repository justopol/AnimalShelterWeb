package pl.shelter.rest.managers;

import pl.shelter.rest.exceptions.entities.AdoptionException;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;
import java.util.UUID;

public interface AdoptionService {
    Adoption findById(UUID id);
    List<Adoption> findAll();
    void addNewAdoption(UUID adopterUuid,UUID animalUuid) throws AdoptionException;
    void finishAdoption(UUID id);
    void cancelAdoption(UUID id);
}
