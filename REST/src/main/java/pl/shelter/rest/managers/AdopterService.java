package pl.shelter.rest.managers;

import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.enums.AdopterType;

import java.util.List;
import java.util.UUID;

public interface AdopterService {
    Adopter findById(UUID id);

    List<Adopter> findAll();

    void addNewAdopter(Adopter adopter);

    void editAdopter(UUID uuid, long originalVersion, Adopter adopterModifications);

    void editStatusOfAdopter(UUID id, AdopterType adopterType);

    void changePassword(UUID uuid, long originalVersion, String password);

    Adopter findAdopterSelf();

    Adopter findAdopterByLogin(String login);
}
