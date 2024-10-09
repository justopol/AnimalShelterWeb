package pl.shelter.web.ctrl.adopters;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.web.ctrl.adoptions.AdoptionAnimalController;
import pl.shelter.web.restclient.AdopterRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ListAdoptersForAdoptionController implements Serializable {

    @Inject
    private AdopterRestClient adopterRestClient;

    @Inject
    private AdoptionAnimalController adoptionAnimalController;

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
        adoptionAnimalController.fetchAnimalsAndSetAdoptioningAdopterId(id);
        return "listAnimalsToAdoptsForAdopter";
    }

}
