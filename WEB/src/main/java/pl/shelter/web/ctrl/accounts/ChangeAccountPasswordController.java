package pl.shelter.web.ctrl.accounts;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.auth.ChangePasswordDto;
import pl.shelter.web.restclient.AccountRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.function.Consumer;

@ConversationScoped
@Named
public class ChangeAccountPasswordController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ChangeAccountPasswordController.class.getName());
    @Inject
    private AccountRestClient accountRestClient;

    @Inject
    private Conversation conversation;

    @Inject
    private FacesContext facesContext;

    private ChangePasswordDto changePasswordDto = new ChangePasswordDto();

    @NotNull
    private String passwordRepeat;

    private String updateAccountLogin;


    private UUID updateAccountId;

    public String getUpdateAccountLogin() {
        return updateAccountLogin;
    }
    public ChangePasswordDto getChangePasswordDto() {
        return changePasswordDto;
    }
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setAccountData(UUID id, String login) {
        if (!conversation.isTransient()) conversation.end();
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);
        updateAccountId = id;
        updateAccountLogin = login;
    }

//    public String changeAccountPasword() {
//        if (null == updateAccountId) {
//            LOG.warning("changeAccountPassword form not properly initialized");
//            return "main";
//        }
//        conversation.end();
//        return changePassword(passwordDto -> accountRestClient.changePassword(updateAccountId, passwordDto),"listAccounts");
//    }
//
//    public String changeSelfPasword() {
//        if (!conversation.isTransient()) conversation.end();
//        return changePassword(passwordDto -> accountRestClient.changeSelfPassword(passwordDto),"success");
//    }

    private String changePassword(Consumer<ChangePasswordDto> restClientInvocation, String returnNavigationCase) {
        if (changePasswordDto.getNewPassword().equals(passwordRepeat)) {
            try {
                restClientInvocation.accept(changePasswordDto);
                return returnNavigationCase;
            } catch (WebApplicationException wae) {
                Logger.getAnonymousLogger().severe(wae.toString());
                if (wae.getResponse().getStatus() >= 500) return "error";
                if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                    I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                    return null;
                } else return "error";
            }
        } else {
            facesContext.validationFailed();
            facesContext.addMessage("changePassword:passwordRepeat", new FacesMessage(I18nUtils.getMessage("error.passwordRepeatNotMatch")));
            return "";
        }

    }
}
