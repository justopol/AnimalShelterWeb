package pl.shelter.rest.repositories;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AccountException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AccountFacade extends AbstractEMFacade<Account> {

    @PersistenceContext
    private EntityManager em;

    public AccountFacade() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(Account entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
            if (pe.getMessage().contains(Account.DB_CONSTRAINT_UNIQUE_EMAIL)) {
                throw AccountException.createForEmailExist(pe);
            } else if (pe.getMessage().contains(Account.DB_CONSTRAINT_UNIQUE_PERSONALID)) {
                throw AccountException.createForPersonalIdExist(pe);
            } else
                throw AppBaseException.createForPersistenceError(pe);
        }
    }

    @Override
    public void remove(Account entity) {
        super.remove(entity);
    }

    @Override
    public Optional<Account> find(UUID id) {
        return super.find(id);
    }

    @Override
    public List<Account> findAll() {
        return super.findAll();
    }

    public Optional<Account> findByLogin(String login) {
        TypedQuery<Account> tq = em.createNamedQuery("Account.findByLogin", Account.class);
        tq.setParameter("login", login);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

}
