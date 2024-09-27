package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void create(Adoption adoption) {
        super.create(adoption);
    }
    @Override
    public Optional<Adoption> find(UUID id) {
        return super.find(id);
    }
    @Override
    public void edit(Adoption entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
            throw AppBaseException.createForPersistenceError(pe);
        }
    }
}
