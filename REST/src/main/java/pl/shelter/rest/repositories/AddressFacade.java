package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.shelter.rest.model.adopters.Address;

import java.util.Optional;
import java.util.UUID;

public class AddressFacade extends AbstractEMFacade<Address> {

    @PersistenceContext
    private EntityManager em;
    public AddressFacade() {
        super(Address.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @Override
    public Optional<Address> find(UUID id) {
        return super.find(id);
    }
}
