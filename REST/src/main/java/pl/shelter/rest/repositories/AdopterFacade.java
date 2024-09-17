package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Adopter;


import java.util.List;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AdopterFacade extends AbstractEMFacade<Adopter> {
    @PersistenceContext
    private EntityManager em;

    public AdopterFacade() {
        super(Adopter.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Adopter> getAdopters() {
        return super.findAll();
    }
}
