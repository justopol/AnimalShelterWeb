package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.dto.animals.AddAnimalCmd;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.dto.animals.EditAnimalCmd;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AnimalRestClient extends AbstractRestClient<AnimalDto> {

    public AnimalRestClient() {
        super(AnimalDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return getBaseTarget().path("animals");
    }

    @Override
    public List<AnimalDto> findAll() {
        return super.findAll();
    }

    @Override
    public AnimalDto find(UUID id) {
        return super.find(id);
    }
    public void edit(UUID id, EditAnimalCmd editAnimalCmd,String group) {
        getTarget().path(group).path(String.valueOf(id)).request()
                .put(Entity.json(editAnimalCmd));
    }
    public void createAnimal(AddAnimalCmd newAnimal) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newAnimal));
    }

}
