package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.animals.Animal;

@TxTracked // To track transactions and method calls
@Transactional(Transactional.TxType.MANDATORY) // To manage application transaction boundaries
public class AnimalFacade extends  AbstractEMFacade<Animal> {

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
}
