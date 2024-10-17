package pl.shelter.rest.repositories;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adoptions.Adoption;
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
    public List<Animal> getForAdoptionAnimals() {
        TypedQuery<Animal> tq = em.createNamedQuery("Animal.findForAdoption", Animal.class);
        return tq.getResultList();
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
