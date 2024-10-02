package pl.shelter.rest.managers;

import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.Admin;
import pl.shelter.rest.model.accounts.Employee;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    void editAccount(UUID uuid, long originalVersion, Account accountModifications);
    void changePassword(UUID uuid, long originalVersion, String password);

    void removeAccount(UUID id);

    Account findById(UUID id);

    Employee findEmployeeById(UUID id);

    List<Employee> findAllEmployees();

    void addNewEmployee(Employee employee);

    Admin addAdmin(Admin admin);

    Admin findAdminById(UUID id);

    List<Admin> findAllAdmins();
    void activateAccount(UUID id);
    void deactivateAccount(UUID id);
    Account findAccountByLogin(String login);
    List<Account> findAllAccounts();
}
