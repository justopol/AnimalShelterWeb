package pl.shelter.web.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import pl.shelter.dto.accounts.adopters.AdopterDto;


import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AdopterRestClient extends AbstractRestClient<AdopterDto> {

    public AdopterRestClient() {
        super(AdopterDto.class);
    }

    @Override
    protected WebTarget getTarget() {
        return getBaseTarget().path("adopters");
    }

    @Override
    public List<AdopterDto> findAll() {
        return super.findAll();
    }

    @Override
    public AdopterDto find(UUID id) {
        return super.find(id);
    }


    public List<AdopterDto> findAdopters() {
        return getTarget().request()
                .get(new GenericType<List<AdopterDto>>() {});
    }

    public AdopterDto findSelf() {
        return getTarget().path("self").request().get(AdopterDto.class);
    }

}
