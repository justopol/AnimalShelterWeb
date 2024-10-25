package pl.shelter.web.ctrl.accounts;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.web.restclient.AccountRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

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

    public String delete(String id) {
        try {
            accountRestClient.remove(UUID.fromString(id));
            init();
            return null;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    public String activate(String id) {
        try {
            accountRestClient.activate(UUID.fromString(id));
            init();
            return null;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }

    }

    public String deactivate(String id) {
        try {
            accountRestClient.deactivate(UUID.fromString(id));
            init();
            return null;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    public String edit(String id) {
        try {
            editAccountController.fetchAccountDataById(UUID.fromString(id));
            return "editAccount";
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }


    public String changePassword(String id, String login) {
        try {
            changeAccountPasswordController.setAccountData(UUID.fromString(id), login);
            return "changeAccountPassword";
        }catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }
}
