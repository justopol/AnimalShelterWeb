package pl.shelter.web.ctrl.animals;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import pl.shelter.dto.animals.AddAnimalCmd;
import pl.shelter.web.restclient.AnimalRestClient;
import pl.shelter.web.utils.I18nUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;

@ConversationScoped
@Named
public class CreateAnimalController implements Serializable {

    @Inject
    private AnimalRestClient animalRestClient;
    @Inject
    private FacesContext facesContext;
    private String forAnimalType;
    private Map<String, String> map = AnimalType.getAnimalTypes();

    private Set<String> animalTypes = map.keySet();
    private boolean castrated = true;

    private AddAnimalCmd newAnimal = new AddAnimalCmd();

    public AddAnimalCmd getNewAnimal() {
        return newAnimal;
    }

    public String createAnimal() {
        try {
            newAnimal.setType(forAnimalType);
            var isCastrated = isCastrated();
            newAnimal.setCastrated(isCastrated());
            return createAnimal(animal -> animalRestClient.createAnimal(animal,map.get(forAnimalType)));
        } catch (WebApplicationException wae) {
            Logger.getAnonymousLogger().severe(wae.toString());
            if (wae.getResponse().getStatus() >= 500) return "error";
            if (I18nUtils.isInternationalizationKeyExist(wae.getMessage())) {
                I18nUtils.emitInternationalizedMessage(null, wae.getMessage());
                return null;
            } else return "error";
        }
    }

    public boolean isMammal() {
        if(forAnimalType==null){
            return false;
        }
        return map.get(forAnimalType).equals("mammal");
    }

    private String createAnimal(Consumer<AddAnimalCmd> restClientInvocation) {
        restClientInvocation.accept(newAnimal);
        return "success";
    }


    public String getForAnimalType() {
        return forAnimalType;
    }

    public void setForAnimalType(String forAnimalType) {
        this.forAnimalType = forAnimalType;
    }

    public Set<String> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(Set<String> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
