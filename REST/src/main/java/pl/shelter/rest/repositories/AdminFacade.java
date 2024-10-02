package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AccountException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AdminFacade extends AbstractEMFacade<Admin> {

    @PersistenceContext
    private EntityManager em;

    public AdminFacade() {
        super(Admin.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Admin entity) {
        try {
            super.create(entity);
        } catch (PersistenceException pe) {
            if (pe.getMessage().contains(Account.DB_CONSTRAINT_UNIQUE_LOGIN)) {
                throw AccountException.createForLoginExist(pe);
            } else if (pe.getMessage().contains(Account.DB_CONSTRAINT_UNIQUE_EMAIL)) {
                throw AccountException.createForEmailExist(pe);
            } else if (pe.getMessage().contains(Account.DB_CONSTRAINT_UNIQUE_PERSONALID)) {
                throw AccountException.createForPersonalIdExist(pe);
            } else
                throw AppBaseException.createForPersistenceError(pe);
        }
    }

    @Override
    public Optional<Admin> find(UUID id) {
        return super.find(id);
    }

    @Override
    public List<Admin> findAll() {
        return super.findAll();
    }
}
