package pl.shelter.web.security;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class AuthenticationClientFilter implements ClientRequestFilter {

    @Inject
    private RestSecurityContext securityContext;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (securityContext.isAuthenticated()) {
            requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, "Bearer " + securityContext.getJwt());
        }
    }

}
