package pl.shelter.web.ctrl.adopters;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.web.ctrl.adoptions.AdoptAnimalController;
import pl.shelter.web.restclient.AdopterRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ListAdoptersController implements Serializable {

    @Inject
    private AdopterRestClient adopterRestClient;

    @Inject
    private AdoptAnimalController listAdoptionController;

    private List<AdopterDto> adopters;

    @PostConstruct
    public void init() { //Public, because we call it from the form

        adopters = adopterRestClient.findAdopters();
    }

    public List<AdopterDto> getAdopters() {
        return adopters;
    }

    public String selectForAdoption(String idStr) {
        var id = UUID.fromString(idStr);
        listAdoptionController.fetchAnimalsAndSetAdoptioningAdopterId(id);
        return "listAnimalsToAdoptsForAdopter";
    }

}
