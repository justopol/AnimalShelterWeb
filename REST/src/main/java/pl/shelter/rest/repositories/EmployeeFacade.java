package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
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
        super.create(employee);
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
