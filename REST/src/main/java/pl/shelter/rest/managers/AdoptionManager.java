package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adoptions.Adoption;
import pl.shelter.rest.repositories.AdoptionFacade;

import java.util.List;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdoptionManager implements AdoptionService{
    @Inject
    private AdoptionFacade adoptionFacade;
    @Override
    public List<Adoption> findAll() {
        return adoptionFacade.getAdoptions();
    }
}
