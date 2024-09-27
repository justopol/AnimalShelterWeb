package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.entities.AdoptionException;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adoptions.Adoption;
import pl.shelter.rest.model.enums.AdoptionStatus;
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

    @Override
    public Adoption findById(UUID id) {
        return adoptionFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Adoption> findAll() {
        return adoptionFacade.getAdoptions();
    }

    @Override
    public void addNewAdoption(UUID adopterUuid,UUID animalUuid) throws AdoptionException {
        var adopter = adopterFacade.find(adopterUuid).orElseThrow(AppBaseException::createForEntityNotFound);
        var animal = animalFacade.find(animalUuid).orElseThrow(AppBaseException::createForEntityNotFound);
        var adoption = new Adoption();
        adoption.createAdoption(LocalDate.now(),adopter,animal);
        adoptionFacade.create(adoption);
    }

    @Override
    public void finishAdoption(UUID id) {
        var adoption = findById(id);
        adoption.getAnimal().setAdoptionStatus(AdoptionStatus.ADOPTED);//todo
        adoptionFacade.edit(adoption);
    }

    @Override
    public void cancelAdoption(UUID id) {
        var adoption = findById(id);
        adoption.getAnimal().setAdoptionStatus(AdoptionStatus.FOR_ADOPTION);//todo
        adoptionFacade.edit(adoption);
    }
}
