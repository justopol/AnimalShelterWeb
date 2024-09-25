package pl.shelter.rest.managers;

import pl.shelter.rest.model.accounts.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee findById(UUID id);

    List<Employee> findAll();

    void addNewEmployee(Employee employee);
    void editEmployee(UUID uuid, long originalVersion, Employee employeeModifications);
    void changePassword(UUID uuid, long originalVersion, String password);

    void removeEmployee(UUID id);
}
