package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AccountException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.accounts.Admin;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.repositories.AccountFacade;
import pl.shelter.rest.repositories.AdminFacade;
import pl.shelter.rest.repositories.EmployeeFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AccountManager implements AccountService{
    @Inject
    private AccountFacade accountFacade;

    @Inject
    private AdminFacade adminFacade;

    @Inject
    private EmployeeFacade employeeFacade;

    @Inject
    private SecurityContext sctx;
    @Override
    public void editAccount(UUID uuid, long originalVersion, Account accountModifications) {
        var account = findById(uuid);
        editAccount(originalVersion, accountModifications, account);
        accountFacade.edit(account);
    }

    @Override
    public void changePassword(UUID uuid, String password) {
        var account = findById(uuid);
        account.setPassword(password);
        accountFacade.edit(account);
    }

    @Override
    public void removeAccount(UUID id) {
        Optional<Account> foundAccount = accountFacade.find(id);
        foundAccount.ifPresent(account -> accountFacade.remove(account));
    }

    @Override
    public Account findById(UUID id) {
        return accountFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }
    @Override
    public Employee findEmployeeById(UUID id) {
        return employeeFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeFacade.getEmployees();
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeFacade.create(employee);
    }
    @Override
    public Admin addAdmin(Admin admin) {
        adminFacade.create(admin);
        return admin;
    }

    @Override
    public Admin findAdminById(UUID id) {
        Optional<Admin> foundAccount = adminFacade.find(id);
        return foundAccount.orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminFacade.findAll();
    }
    @Override
    public void activateAccount(UUID id) {
        setAccountActive(id, true);
    }
    @Override
    public void deactivateAccount(UUID id) {
        setAccountActive(id, false);
    }
    @Override
    public Account findAccountByLogin(String login) {
        return accountFacade.findByLogin(login).orElseThrow(AppBaseException::createForEntityNotFound);
    }
    @Override
    public List<Account> findAllAccounts() {
        return accountFacade.findAll();
    }
    @Override
    public Account findAccountSelf() {
        return findAccountByLogin(sctx.getCallerPrincipal().getName());
    }
    @Override
    public void changeSelfPassword(String hashedOldPassword, String hashedNewPassword) {
        Account modifiedAccount = findAccountSelf();
        if(!modifiedAccount.getPassword().equals(hashedOldPassword))
            throw AccountException.createForOldPasswordMismatch();
        modifiedAccount.setPassword(hashedNewPassword);
        accountFacade.edit(modifiedAccount);
    }



    private static void editAccount(long originalVersion, Account accountModifications, Account modifiedAccount) {
        if (originalVersion != modifiedAccount.getVersion())
            throw AppBaseException.createForOptimisticLock();

        if (null != accountModifications.getEmail()) {
            modifiedAccount.setEmail(accountModifications.getEmail());
        }
        if (null != accountModifications.getFirstName()) {
            modifiedAccount.setFirstName(accountModifications.getFirstName());
        }
        if (null != accountModifications.getLastName()) {
            modifiedAccount.setLastName(accountModifications.getLastName());
        }
    }
    private void setAccountActive(UUID id, boolean active) {
        Account modifiedAccount = findById(id);
        modifiedAccount.setActive(active);
        accountFacade.edit(modifiedAccount);
    }

}
