package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.repositories.EmployeeFacade;

import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class EmployeeManager implements EmployeeService {

    @Inject
    private EmployeeFacade employeeFacade;

    @Override
    public Employee findById(UUID id) {
        return employeeFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Employee> findAll() {
        return employeeFacade.getEmployees();
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeFacade.create(employee);
    }

    @Override
    public void editEmployee(UUID uuid, long originalVersion, Employee employeeModifications) {
        var employee = findById(uuid);
        editEmployee(originalVersion, employeeModifications, employee);
        employeeFacade.edit(employee);
    }

    @Override
    public void changePassword(UUID uuid, long originalVersion, String password) {
        var employee = findById(uuid);
        if (originalVersion != employee.getVersion())
            throw AppBaseException.createForOptimisticLock();
        employee.setPassword(password);
        employeeFacade.edit(employee);
    }

    @Override
    public void removeEmployee(UUID id) {
        var employee = findById(id);
        employeeFacade.remove(employee);
    }

    private static void editEmployee(long originalVersion, Employee employeeModifications, Employee modifiedEmployee) {
        if (originalVersion != modifiedEmployee.getVersion())
            throw AppBaseException.createForOptimisticLock();

        if (null != employeeModifications.getEmail()) {
            modifiedEmployee.setEmail(employeeModifications.getEmail());
        }
        if (null != employeeModifications.getFirstName()) {
            modifiedEmployee.setFirstName(employeeModifications.getFirstName());
        }
        if (null != employeeModifications.getLastName()) {
            modifiedEmployee.setLastName(employeeModifications.getLastName());
        }


    }
}
