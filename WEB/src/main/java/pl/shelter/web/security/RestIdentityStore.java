package pl.shelter.web.security;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import pl.shelter.dto.auth.UsernamePasswordDto;
import pl.shelter.web.producers.RestClientProducer;
import com.nimbusds.jwt.SignedJWT;


import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@ApplicationScoped
public class RestIdentityStore implements IdentityStore {

    @Inject
    private RestSecurityContext securityContext;

    private static final Logger LOG = Logger.getLogger(RestIdentityStore.class.getName());

    @Inject
    private RestClientProducer clientProducer;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
            authenticateWitUsernamePassword(usernamePasswordCredential.getCaller(), usernamePasswordCredential.getPasswordAsString());
            return (securityContext.isAuthenticated() ?
                    new CredentialValidationResult(securityContext.getUserName(), new HashSet<>(securityContext.getGroups()))
                    : CredentialValidationResult.INVALID_RESULT);
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;

    }

    private void authenticateWitUsernamePassword(String username, String password) {
        UsernamePasswordDto authData = new UsernamePasswordDto(username, password);
        Response authResponse = clientProducer.getRestTarget().path("auth").request().post(Entity.json(authData));
        if (authResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            JsonObject responseEntity = authResponse.readEntity(JsonObject.class);
            JsonString jwt = responseEntity.getJsonString("entity");
            String jwtAsString = jwt.getString();
            try {
                SignedJWT signedJWT = SignedJWT.parse(jwtAsString);
                securityContext.setUserName(signedJWT.getJWTClaimsSet().getSubject());
                securityContext.setGroups(signedJWT.getJWTClaimsSet().getStringListClaim("groups"));
                securityContext.setJwt(jwtAsString);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
