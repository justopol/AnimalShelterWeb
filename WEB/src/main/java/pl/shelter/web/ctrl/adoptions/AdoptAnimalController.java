package pl.shelter.web.ctrl.adoptions;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.web.restclient.AdopterRestClient;
import pl.shelter.web.restclient.AdoptionRestClient;
import pl.shelter.web.restclient.AnimalRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class AdoptAnimalController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AdoptAnimalController.class.getName());
    @Inject
    private AnimalRestClient animalRestClient;

    @Inject
    private AdoptionRestClient adoptionRestClient;

    @Inject
    private AdopterRestClient adopterRestClient;

    @Inject
    private Conversation conversation;


    private UUID adoptioningAdopterId;



    private List<AnimalDto> animalsForAdoption;

    public void fetchAnimalsAndSetAdoptioningAdopterId(UUID id) {
        fetchAnimals();
        adoptioningAdopterId = id;
    }

    public String fetchAnimalsForSelf() {
        fetchAnimals();
        return "listAnimalsToAdoptionForSelf";
    }

    private void fetchAnimals() {
        if(!conversation.isTransient()) conversation.end();
        conversation.begin();
        conversation.setTimeout(1000*60*10);
        animalsForAdoption = animalRestClient.findAll();
    }

    public List<AnimalDto> getAnimalsForAdoption() {
        return animalsForAdoption;
    }

    public String adoptionAnimalForAdopter(String selectedAnimalIdStr) {
        var selectedAnimalId = UUID.fromString(selectedAnimalIdStr);
        if (null == adoptioningAdopterId) {
            LOG.warning("adoptionAnimalForAdopter form not properly initialized");
            return "main";
        }
        return adoptionAnimal(selectedAnimalId, (animalId) -> adoptionRestClient.adoptAnimalForAdopter(animalId, adoptioningAdopterId));
    }
//    public String adoptionAnimalForSelf(UUID selectedAnimalId) {
//        return adoptionAnimal(selectedAnimalId, (animalId) -> adoptionRestClient.adoptionAnimalForSelf(animalId));
//    }
    private String adoptionAnimal(UUID selectedAnimalId, Consumer<UUID> adoptionClientInvocation) {
        conversation.end();
        if (null == animalsForAdoption) {
            LOG.warning("adoptionAnimalForAdopter/Self form not properly initialized");
            return "main";
        }
        try {
            adoptionClientInvocation.accept(selectedAnimalId);
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());

            if (wae.getResponse().getStatus()>=500) return "error";

            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null , wae.getMessage());
                return null;
            }
            else return "error";
        }
        return "success";
    }
}
