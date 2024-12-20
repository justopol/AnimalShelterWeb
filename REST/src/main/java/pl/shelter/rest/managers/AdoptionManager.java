package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AdoptionException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adoptions.Adoption;
import pl.shelter.rest.repositories.AdopterFacade;
import pl.shelter.rest.repositories.AdoptionFacade;
import pl.shelter.rest.repositories.AnimalFacade;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdoptionManager implements AdoptionService{
    @Inject
    private AdoptionFacade adoptionFacade;
    @Inject
    private AdopterFacade adopterFacade;
    @Inject
    private AnimalFacade animalFacade;
    @Inject
    private SecurityContext sctx;

    @Override
    public Adoption findById(UUID id) {
        return adoptionFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Adoption> findAdoptions(boolean includeUnderAdoption, boolean includeAdopted, UUID forAdopterId) {
        Adopter forAdopter = (null != forAdopterId ?
                adopterFacade.find(forAdopterId).orElseThrow(AppBaseException::createForEntityNotFound)
                : null);
        return adoptionFacade.matchAdoptions(includeUnderAdoption, includeAdopted, forAdopter);
    }

    @Override
    public List<Adoption> findSelfAdoptions() {
        return adoptionFacade.findByLogin(sctx.getCallerPrincipal().getName());
    }

    @Override
    public Adoption addNewAdoption(UUID adopterUuid,UUID animalUuid) throws AdoptionException {
        var adopter = adopterFacade.find(adopterUuid).orElseThrow(AppBaseException::createForEntityNotFound);
        if(adopter.getMaxAnimals() <= adoptionFacade.countAdoptionsByAdopter(adopter)) throw AdoptionException.createForAdoptionLimitReached();
        var animal = animalFacade.find(animalUuid).orElseThrow(AppBaseException::createForEntityNotFound);
        var adoption = new Adoption();
        adoption.createAdoption(LocalDate.now(),adopter,animal);
        adoptionFacade.create(adoption);
        return adoption;
    }

    @Override
    public void finishAdoption(UUID id) {
        var adoption = findById(id);
        adoption.finishAdoption(LocalDate.now());
        adoptionFacade.edit(adoption);
    }

    @Override
    public void cancelAdoption(UUID id) {
        var adoption = findById(id);
        adoption.cancelAdoption();
        adoptionFacade.edit(adoption);
        adoptionFacade.remove(adoption);
    }
}
