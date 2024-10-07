package pl.shelter.rest.endpoints;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.shelter.dto.auth.UsernamePasswordDto;
import pl.shelter.rest.managers.AuthService;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.utils.ValidationMessages;
import pl.shelter.rest.utils.security.HashGenerator;
import pl.shelter.rest.utils.security.JwtGenerator;

@Path("auth")
@PermitAll
public class LoginAuthEndpoint {

    private AuthService authService;

    private JwtGenerator jwtGenerator;
    private HashGenerator hashGenerator;

    @Inject
    public LoginAuthEndpoint(AuthService authService,
                             JwtGenerator jwtGenerator,
                             HashGenerator hashGenerator) {
        this.authService = authService;
        this.jwtGenerator = jwtGenerator;
        this.hashGenerator = hashGenerator;
    }

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                         @Valid UsernamePasswordDto authData) {
        Account account = authService.authenticate(authData.getUsername(), hashGenerator.generateHash(authData.getPassword()));
        return Response.ok()
                .entity(Entity.json(jwtGenerator.generateJwt(account)))
                .build();
    }

}
