package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;

import java.util.Optional;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AuthFacade extends AbstractEMFacade<Account> {

    @PersistenceContext
    private EntityManager em;

    public AuthFacade() {
        super(Account.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Optional<Account> findMatchingAndActiveAccount(String login, String password) {
        TypedQuery<Account> tq = em.createNamedQuery("Account.findByLoginAndPasswordAndActiveTrue", Account.class);
        tq.setParameter("login", login);
        tq.setParameter("password", password); try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

}
