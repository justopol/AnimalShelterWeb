package pl.shelter.web.ctrl.accounts;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.web.restclient.AccountRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ListAccountsController implements Serializable {
    @Inject
    private AccountRestClient accountRestClient;
    @Inject
    private EditAccountController editAccountController;

    @Inject
    private ChangeAccountPasswordController changeAccountPasswordController;

    private List<AccountDto> accounts;
    @PostConstruct
    public void init() { //Public, because we call it from the form
        accounts = accountRestClient.findAll();
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }
    public void delete(String id) {
        accountRestClient.remove(UUID.fromString(id));
        init();
    }

    public void activate(String id) {
        accountRestClient.activate(UUID.fromString(id));
        init();
    }
    public void deactivate(String id) {
        accountRestClient.deactivate(UUID.fromString(id));
        init();
    }

    public String edit(String id) {
        editAccountController.fetchAccountDataById(UUID.fromString(id));
        return "editAccount";
    }
    public String changePassword(String id, String login) {
        changeAccountPasswordController.setAccountData(UUID.fromString(id), login);
        return "changeAccountPassword";
    }
}
