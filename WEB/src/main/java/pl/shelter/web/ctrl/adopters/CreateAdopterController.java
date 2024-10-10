package pl.shelter.web.ctrl.adopters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.adopters.AddAdopterCmd;
import pl.shelter.web.restclient.AdopterRestClient;

import pl.shelter.web.utils.I18nUtils;

import java.util.logging.Logger;

@RequestScoped
@Named
public class CreateAdopterController {
    @NotNull
    private String defaultPassword="1234567";
    @Inject
    private AdopterRestClient adopterRestClient;

    private AddAdopterCmd newAdopter = new AddAdopterCmd();

    public AddAdopterCmd getNewAdopter() {
        return newAdopter;
    }

    public String createAdopter() {
        try {
            newAdopter.setPassword(defaultPassword);
            adopterRestClient.createAdopter(newAdopter);
            return "success";
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }
}
