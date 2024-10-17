package pl.shelter.web.ctrl.adopters;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.web.restclient.AdopterRestClient;
import pl.shelter.web.restclient.AdoptionRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ListSelfAdoptionController implements Serializable {

    @Inject
    private AdoptionRestClient adoptionRestClient;

    @Inject
    private AdopterRestClient adopterRestClient;

    private List<AdoptionDto> adoptions;

    private AdopterDto adopter;

    private boolean includeUnderAdoption = true;
    private boolean includeAdopted = false;

    private UUID forAdopterId;

    @PostConstruct
    public void init() {
        adopter = adopterRestClient.findSelf();
        adoptions = adoptionRestClient.match(includeUnderAdoption, includeAdopted,UUID.fromString(adopter.getAdopterId()));
    }

    public boolean isIncludeUnderAdoption() {
        return includeUnderAdoption;
    }

    public void setIncludeUnderAdoption(boolean includeUnderAdoption) {
        this.includeUnderAdoption = includeUnderAdoption;
    }

    public List<AdoptionDto> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<AdoptionDto> adoptions) {
        this.adoptions = adoptions;
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
