package pl.shelter.rest.managers;

import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;

public interface AdoptionService {
    List<Adoption> findAll();
}
