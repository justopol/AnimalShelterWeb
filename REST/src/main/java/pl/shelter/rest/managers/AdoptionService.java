package pl.shelter.rest.managers;

import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;
import java.util.UUID;

public interface AdoptionService {
    Adoption findById(UUID id);
    List<Adoption> findAll();
    void addNewAdoption(Adoption adoption);
    void finishAdoption(UUID id);
    void cancelAdoption(UUID id);
}
