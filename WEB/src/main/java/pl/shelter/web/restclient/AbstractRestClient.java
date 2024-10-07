package pl.shelter.web.restclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.AbstractDto;
import pl.shelter.web.restclient.filters.ApplicationClientResponseFilter;
import pl.shelter.web.restclient.filters.AuthenticationClientRequestFilter;

import java.util.List;
import java.util.UUID;

public abstract class AbstractRestClient<T extends AbstractDto> {

    private Class<T> entityClass;

    public AbstractRestClient(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private final Client restClient = ClientBuilder.newClient()
            .register(ApplicationClientResponseFilter.class)
            .register(AuthenticationClientRequestFilter.class);

    private final WebTarget baseTarget = restClient.target("http://localhost:8080/REST-1.0-SNAPSHOT/api/");

    protected WebTarget getBaseTarget() {
        return baseTarget;
    }

    protected abstract WebTarget getTarget();

    protected void create(T entity) {
        getTarget().request(MediaType.APPLICATION_JSON)
                .post(Entity.json(entity));
    }

    protected List<T> findAll() {
        return getTarget().request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<T>>() {});
    }

    protected T find(UUID id) {
        return getTarget().path(String.valueOf(id)).request(MediaType.APPLICATION_JSON)
                .get(entityClass);
    }

    protected void remove(UUID id) {
        getTarget().path(String.valueOf(id)).request()
                .delete();
    }


}

