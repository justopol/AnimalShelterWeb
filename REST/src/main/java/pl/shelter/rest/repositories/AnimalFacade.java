package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.shelter.rest.model.animals.Animal;

public class AnimalFacade extends  AbstractEMFacade<Animal> {

    @PersistenceContext
    private EntityManager em;

    public AnimalFacade(Class<Animal> entityClass) {
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
