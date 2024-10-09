package pl.shelter.rest.managers;

import pl.shelter.rest.exceptions.AdoptionException;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;
import java.util.UUID;

public interface AdoptionService {
    Adoption findById(UUID id);
    List<Adoption> findAdoptions(boolean includeUnderAdoption, boolean includeAdopted, UUID forAdopterId);
    List<Adoption> findSelfAdoptions();
    Adoption addNewAdoption(UUID adopterUuid,UUID animalUuid) throws AdoptionException;
    void finishAdoption(UUID id);
    void cancelAdoption(UUID id);
}
