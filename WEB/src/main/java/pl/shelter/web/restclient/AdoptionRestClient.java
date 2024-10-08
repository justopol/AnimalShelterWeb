package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.adoptions.AddAdoptionCmd;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;


import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AdoptionRestClient extends AbstractRestClient<AdoptionDto> {

    public AdoptionRestClient() {
        super(AdoptionDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return getBaseTarget().path("adoptions");
    }

    public AdoptionDto adoptAnimalForAdopter(UUID animalId, UUID adopterId) {
        return getTarget().request()
                .post(Entity.entity(new AddAdoptionCmd(animalId, adopterId),MediaType.APPLICATION_JSON))
                .readEntity(AdoptionDto.class);
    }

    public AdoptionDto adoptionAnimalForSelf(UUID vehicleId) {
        return getTarget().path(String.valueOf(vehicleId)).request()
                .post(Entity.entity(null,MediaType.TEXT_PLAIN_TYPE))
                .readEntity(AdoptionDto.class);
    }

    public List<AdoptionDto> match(boolean includeCurrent, boolean includeArchive, UUID forAdopterId) {
        return getTarget()
                .queryParam("includeCurrent", includeCurrent)
                .queryParam("includeArchive", includeArchive)
                .queryParam("forAdopterId", forAdopterId)
                .request().get().readEntity(new GenericType<List<AdoptionDto>>() {});
    }

    public void finish(UUID id) {
        getTarget().path(String.valueOf(id)).path("finish").request().post(Entity.entity(null,MediaType.TEXT_PLAIN_TYPE));
    }

    public void cancel(UUID id) {
        getTarget().path(String.valueOf(id)).path("cancel").request().post(Entity.entity(null,MediaType.TEXT_PLAIN_TYPE));
    }


    @Override
    public AdoptionDto find(UUID id) {
        return super.find(id);
    }

    @Override
    public void remove(UUID id) {
        super.remove(id);
    }

}
