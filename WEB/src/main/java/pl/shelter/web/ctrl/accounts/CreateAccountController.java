package pl.shelter.web.ctrl.accounts;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.web.restclient.AdminRestClient;
import pl.shelter.web.restclient.EmployeeRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.util.function.Consumer;
import java.util.logging.Logger;

@RequestScoped
@Named
public class CreateAccountController {
    @NotNull
    private String passwordRepeat;
    @Inject
    private EmployeeRestClient employeeRestClient;
    @Inject
    private AdminRestClient adminRestClient;
    @Inject
    private FacesContext facesContext;

    private AddAccountCmd newAccount = new AddAccountCmd();

    public AddAccountCmd getNewAccount() {
        return newAccount;
    }

    public String createEmployee() {
        try {
            return createAccount(account -> employeeRestClient.createEmployee(account));
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    public String createAdmin() {
        try {
            return createAccount(account -> adminRestClient.createAdmin(account));
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    private String createAccount(Consumer<AddAccountCmd> restClientInvocation) {
        if (null != newAccount && null != newAccount.getPassword() && newAccount.getPassword().equals(passwordRepeat)) {
            restClientInvocation.accept(newAccount);
            return "success";
        } else {
            facesContext.validationFailed();
            facesContext.addMessage("createAccount:passwordRepeat", new FacesMessage(I18nUtils.getMessage("error.passwordRepeatNotMatch")));
            return "";
        }

    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
