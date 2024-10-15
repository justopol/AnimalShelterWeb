package pl.shelter.web.ctrl.animals;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.dto.enums.Castrated;
import pl.shelter.web.restclient.AnimalRestClient;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ListAnimalsController implements Serializable {
    @Inject
    private AnimalRestClient animalRestClient;
    @Inject
    private EditAnimalController editAnimalController;

    private List<AnimalDto> animals;
    @PostConstruct
    public void init() {
        animals = animalRestClient.findAll();
    }

    public List<AnimalDto> getAnimals() {
        return animals;
    }

    public String edit(String id,String type) {
        editAnimalController.fetchAnimalDataById(UUID.fromString(id), type);
        return "editAnimal";
    }


}
