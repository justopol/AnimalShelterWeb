package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AnimalFacade extends AbstractEMFacade<Animal> {

    @PersistenceContext
    private EntityManager em;

    public AnimalFacade() {
        super(Animal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(Animal animal) {
        super.create(animal);
    }

    public List<Animal> getAnimals() {
        return super.findAll();
    }

    @Override
    public Optional<Animal> find(UUID id) {
        return super.find(id);
    }
    @Override
    public void edit(Animal entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
           throw AppBaseException.createForPersistenceError(pe);
        }
    }
}
