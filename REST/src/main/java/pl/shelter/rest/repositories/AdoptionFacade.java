package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AdoptionFacade extends AbstractEMFacade<Adoption> {
    @PersistenceContext
    private EntityManager em;

    public AdoptionFacade() {
        super(Adoption.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public List<Adoption> getAdoptions() {
        return super.findAll();
    }
}
