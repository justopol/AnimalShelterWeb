package pl.shelter.web.producers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import pl.shelter.web.errors.RestClientExceptionHandler;
import pl.shelter.web.security.AuthenticationClientFilter;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class RestClientProducer {

    @Inject
    @ConfigProperty(name = "rest.service.url", defaultValue = "http://localhost:8080/REST-1.0-SNAPSHOT/api/")
    private String target;

    public WebTarget getRestTarget() {
        WebTarget webTarget = ClientBuilder.newClient()
                .target(target);
        webTarget.register(AuthenticationClientFilter.class)
                .register(RestClientExceptionHandler.class);
        return webTarget;
    }
}
