package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adopters.AdopterType;
import pl.shelter.rest.repositories.AdopterFacade;

import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdopterManager implements AdopterService{

    @Inject
    private AdopterFacade adopterFacade;

    @Override
    public Adopter findById(UUID id) {
        return null;
    }

    @Override
    public List<Adopter> findAll() {
        return adopterFacade.getAdopters();
    }

    @Override
    public Adopter findByLogin(String login) {
        return null;
    }

    @Override
    public Adopter findSelf() {
        return null;
    }

    @Override
    public void addNewAdopter(Adopter adopter) {

    }

    @Override
    public void editAdopter(UUID uuid, long originalVersion, Adopter adopterModifications) {

    }

    @Override
    public void editStatusOfAdopter(UUID id, AdopterType adopterType) {

    }
}
