package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;

import java.util.List;

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
}
