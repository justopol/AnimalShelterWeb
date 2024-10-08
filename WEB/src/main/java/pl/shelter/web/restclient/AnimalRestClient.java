package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.animals.AnimalDto;

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

}
