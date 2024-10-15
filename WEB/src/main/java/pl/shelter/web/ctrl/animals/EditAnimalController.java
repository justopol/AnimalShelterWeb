package pl.shelter.web.ctrl.animals;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.dto.animals.EditAnimalCmd;
import pl.shelter.dto.enums.Castrated;
import pl.shelter.web.restclient.AnimalRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class EditAnimalController implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditAnimalController.class.getName());
    @Inject
    private AnimalRestClient animalRestClient;

    @Inject
    private Conversation conversation;

    private EditAnimalCmd updateAnimalCmd = new EditAnimalCmd(0, "-",0, "-", "NOT_POSSESS");

    private UUID updateAnimalId;

    private String updateAnimalDescription = "-";
    private String forCastratedId;
    private String group;
    private ArrayList<String> castratedTypes=new ArrayList<>(Arrays.asList(Castrated.CASTRATED.toString(),Castrated.NOT_CASTRATED.toString(),Castrated.NOT_POSSESS.toString()));
    private Map<String,String> map = AnimalType.getAnimalTypes();

    public void fetchAnimalDataById(UUID id, String type) {
        updateAnimalId = id;
        this.group=map.get(type);
        if (!conversation.isTransient()) conversation.end();
        fetchAnimalData(() -> animalRestClient.find(id));
    }


    private void fetchAnimalData(Supplier<AnimalDto> restClientInvocation) {
        AnimalDto viewUpdatedAnimal = restClientInvocation.get();
        updateAnimalId = viewUpdatedAnimal.getAnimalId();
        conversation.begin();
        conversation.setTimeout(1000 * 60 * 10);
        updateAnimalCmd = new EditAnimalCmd(
                viewUpdatedAnimal.getAnimalVersion(),
                viewUpdatedAnimal.getType(),
                viewUpdatedAnimal.getAge(),
                viewUpdatedAnimal.getName(),
                viewUpdatedAnimal.getCastrated());
        updateAnimalDescription = viewUpdatedAnimal.getName();
    }

    public EditAnimalCmd getUpdateAnimalCmd() {
        return updateAnimalCmd;
    }

    public String getUpdateAnimalDescription() {
        return updateAnimalDescription;
    }

    public String updateAnimalById() {
        updateAnimalCmd.setCastrated(Castrated.valueOf(forCastratedId));
        return updateAnimal((updateAnimalCmd) -> animalRestClient.edit(updateAnimalId, updateAnimalCmd, group),
            () -> animalRestClient.find(updateAnimalId),
            "listAnimals");
    }


    public String updateAnimal(Consumer<EditAnimalCmd> restClientInvocationUpdate, Supplier<AnimalDto> restClientInvocationFetch, String returnNavigationCase) {
        if (null == updateAnimalId) {
            LOG.warning("editAnimal form not properly initialized");
            return "main";
        }
        conversation.end();
        try {
            restClientInvocationUpdate.accept(updateAnimalCmd);
            return returnNavigationCase;
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                fetchAnimalData(restClientInvocationFetch);
                return null;
            } else return "error";
        }
    }

    public String getForCastratedId() {
        return forCastratedId;
    }

    public void setForCastratedId(String forCastratedId) {
        this.forCastratedId = forCastratedId;
    }

    public ArrayList<String> getCastratedTypes() {
        return castratedTypes;
    }

    public void setCastratedTypes(ArrayList<String> castratedTypes) {
        this.castratedTypes = castratedTypes;
    }

}
