package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adoptions.Adoption;
import pl.shelter.rest.model.enums.AdoptionStatus;
import pl.shelter.rest.repositories.AdoptionFacade;

import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdoptionManager implements AdoptionService{
    @Inject
    private AdoptionFacade adoptionFacade;

    @Override
    public Adoption findById(UUID id) {
        return adoptionFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Adoption> findAll() {
        return adoptionFacade.getAdoptions();
    }

    @Override
    public void addNewAdoption(Adoption adoption) {
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
