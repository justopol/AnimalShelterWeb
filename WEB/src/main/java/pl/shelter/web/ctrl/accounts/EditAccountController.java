package pl.shelter.web.ctrl.accounts;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.dto.accounts.PersonalIdDto;
import pl.shelter.web.restclient.AccountRestClient;
import pl.shelter.web.utils.I18nUtils;


import java.io.Serializable;
import java.util.UUID;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class EditAccountController implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditAccountController.class.getName());
    @Inject
    private AccountRestClient accountRestClient;

    @Inject
    private Conversation conversation;

    private EditAccountCmd updateAccountCmd = new EditAccountCmd(0, "-", "-", "-");

    private UUID updateAccountId;

    private String updateAccountDescription = "-";

    public void fetchAccountDataById(UUID id) {
        if (!conversation.isTransient()) conversation.end();
        fetchAccountData(() -> accountRestClient.find(id));
    }

//    public String fetchSelfAccountData() {
//        if (!conversation.isTransient()) conversation.end();
//        fetchAccountData(() -> accountRestClient.findSelf());
//        return "editSelfAccount";
//    }

    private void fetchAccountData(Supplier<AccountDto> restClientInvocation) {
        AccountDto viewUpdatedAccount = restClientInvocation.get();
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);
        updateAccountId = viewUpdatedAccount.getId();
        updateAccountCmd = new EditAccountCmd(
                viewUpdatedAccount.getVersion(),
                viewUpdatedAccount.getEmail(),
                viewUpdatedAccount.getFirstName(),
                viewUpdatedAccount.getLastName());
        updateAccountDescription = I18nUtils.getMessage(viewUpdatedAccount.getRole()) + ": " + viewUpdatedAccount.getLogin();
    }

    public EditAccountCmd getUpdateAccountCmd() {
        return updateAccountCmd;
    }

    public String getUpdateAccountDescription() {
        return updateAccountDescription;
    }

    public String updateAccountById() {
        return updateAccount((updateAccountCmd) -> accountRestClient.edit(updateAccountId, updateAccountCmd),
            () -> accountRestClient.find(updateAccountId),
            "listAccounts");
    }

//    public String updateSelfAccount() {
//        return updateAccount((updateAccountCmd) -> accountRestClient.edit(updateAccountId, updateAccountCmd),
//            () -> accountRestClient.findSelf(),
//            "success");
//    }

    public String updateAccount(Consumer<EditAccountCmd> restClientInvocationUpdate, Supplier<AccountDto> restClientInvocationFetch, String returnNavigationCase) {
        if (null == updateAccountId) {
            LOG.warning("editAccount form not properly initialized");
            return "main";
        }
        conversation.end();
        try {
            restClientInvocationUpdate.accept(updateAccountCmd);
            return returnNavigationCase;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                fetchAccountData(restClientInvocationFetch);
                return null;
            } else return "error";
        }
    }
}
