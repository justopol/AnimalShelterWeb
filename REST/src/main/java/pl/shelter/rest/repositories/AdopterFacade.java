package pl.shelter.rest.repositories;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Adopter;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Override
    public Optional<Adopter> find(UUID id) {
        return super.find(id);
    }
    public void create(Adopter adopter) {
        super.create(adopter);
    }
    @Override
    public void edit(Adopter entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
            throw AppBaseException.createForPersistenceError(pe);
        }
    }
    public Optional<Adopter> findByLogin(String login) {
        TypedQuery<Adopter> tq = em.createNamedQuery("Adopter.findByLogin", Adopter.class);
        tq.setParameter("login", login);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }
}
