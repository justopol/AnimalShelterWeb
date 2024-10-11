package pl.shelter.web.ctrl.adopters;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adopters.EditAdopterCmd;
import pl.shelter.web.restclient.AdopterRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class EditAdopterController implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditAdopterController.class.getName());
    @Inject
    private AdopterRestClient adopterRestClient;

    @Inject
    private Conversation conversation;

    private EditAdopterCmd updateAdopterCmd = new EditAdopterCmd(0, "-", "-", "-","-","-","-");

    private UUID updateAdopterId;

    private String updateAdopterDescription = "-";

    public void fetchAdopterDataById(UUID id) {
        updateAdopterId = id;
        if (!conversation.isTransient()) conversation.end();
        fetchAdopterData(() -> adopterRestClient.find(id));
    }

//    public String fetchSelfAccountData() {
//        if (!conversation.isTransient()) conversation.end();
//        fetchAccountData(() -> accountRestClient.findSelf());
//        return "editSelfAccount";
//    }

    private void fetchAdopterData(Supplier<AdopterDto> restClientInvocation) {
        AdopterDto viewUpdatedAdopter = restClientInvocation.get();
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);
        updateAdopterCmd = new EditAdopterCmd(
                viewUpdatedAdopter.getVersion(),
                viewUpdatedAdopter.getFirstName(),
                viewUpdatedAdopter.getLastName(),
                viewUpdatedAdopter.getEmail(),
                viewUpdatedAdopter.getStreetName(),
                viewUpdatedAdopter.getStreetNumber(),
                viewUpdatedAdopter.getCity());
        updateAdopterDescription = viewUpdatedAdopter.getLogin();
    }

    public EditAdopterCmd getUpdateAdopterCmd() {
        return updateAdopterCmd;
    }

    public String getUpdateAdopterDescription() {
        return updateAdopterDescription;
    }

    public String updateAdopterById() {
        return updateAdopter((updateAdopterCmd) -> adopterRestClient.edit(updateAdopterId, updateAdopterCmd),
            () -> adopterRestClient.find(updateAdopterId),
            "listAdopters");
    }

//    public String updateSelfAccount() {
//        return updateAccount((updateAccountCmd) -> accountRestClient.edit(updateAccountId, updateAccountCmd),
//            () -> accountRestClient.findSelf(),
//            "success");
//    }

    public String updateAdopter(Consumer<EditAdopterCmd> restClientInvocationUpdate, Supplier<AdopterDto> restClientInvocationFetch, String returnNavigationCase) {
        if (null == updateAdopterId) {
            LOG.warning("editAdopter form not properly initialized");
            return "main";
        }
        conversation.end();
        try {
            restClientInvocationUpdate.accept(updateAdopterCmd);
            return returnNavigationCase;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                fetchAdopterData(restClientInvocationFetch);
                return null;
            } else return "error";
        }
    }
}
