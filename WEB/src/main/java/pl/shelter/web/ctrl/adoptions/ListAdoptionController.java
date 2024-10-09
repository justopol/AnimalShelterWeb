package pl.shelter.web.ctrl.adoptions;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.web.restclient.AdopterRestClient;
import pl.shelter.web.restclient.AdoptionRestClient;
import pl.shelter.web.utils.I18nUtils;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ListAdoptionController implements Serializable {

    @Inject
    private AdoptionRestClient adoptionRestClient;

    @Inject
    private AdopterRestClient adopterRestClient;

    private List<AdoptionDto> adoptions;

    private List<AdopterDto> adopters;

    private boolean includeUnderAdoption = true;
    private boolean includeAdopted = false;

    private UUID forAdopterId;

    @PostConstruct
    public void init() {
        adoptions = adoptionRestClient.match(includeUnderAdoption, includeAdopted, forAdopterId);
        adopters = adopterRestClient.findAll();
    }

    public void finish(String id) {
        try {
            adoptionRestClient.finish(UUID.fromString(id));
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());

            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
            } else {
                I18nUtils.emitGeneralErrorMessage();
            }
        }
        init();
    }

    public void cancel(String id) {
        try {
            adoptionRestClient.cancel(UUID.fromString(id));
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());

            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
            } else {
                I18nUtils.emitGeneralErrorMessage();
            }
        }
        init();
    }

    public List<AdoptionDto> getAdoptions() {
        return adoptions;
    }

    public List<AdopterDto> getAdopters() {
        return adopters;
    }

    public boolean isIncludeUnderAdoption() {
        return includeUnderAdoption;
    }

    public void setIncludeUnderAdoption(boolean includeUnderAdoption) {
        this.includeUnderAdoption = includeUnderAdoption;
    }

    public boolean isIncludeAdopted() {
        return includeAdopted;
    }

    public void setIncludeAdopted(boolean includeAdopted) {
        this.includeAdopted = includeAdopted;
    }

    public String getForAdopterId() {
        return String.valueOf(forAdopterId);
    }

    public void setForAdopterId(String forAdopterId) {
        if (null != forAdopterId) {
            this.forAdopterId = UUID.fromString(forAdopterId);
        }
    }
}
