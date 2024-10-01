package pl.shelter.web.ctrl.accounts;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.web.restclient.EmployeeRestClient;

@RequestScoped
@Named
public class CreateAccountController {
    @Inject
    private EmployeeRestClient employeeRestClient;

    private AddAccountCmd newAccount = new AddAccountCmd();

    public AddAccountCmd getNewAccount() {
        return newAccount;
    }

    public String createEmployee(){
        employeeRestClient.createEmployee(newAccount);
        return "success";
    }
}
