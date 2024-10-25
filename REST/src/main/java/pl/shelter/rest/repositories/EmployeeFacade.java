package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AccountException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class EmployeeFacade extends AbstractEMFacade<Employee> {
    @PersistenceContext
    private EntityManager em;

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Employee> getEmployees() {
        return super.findAll();
    }
    @Override
    public Optional<Employee> find(UUID id) {
        return super.find(id);
    }
    public void create(Employee employee) {
        try {
            super.create(employee);
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
    public void remove(Employee employee) {
        super.remove(employee);
    }

    @Override
    public void edit(Employee entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
            throw AppBaseException.createForPersistenceError(pe);
        }
    }
}
