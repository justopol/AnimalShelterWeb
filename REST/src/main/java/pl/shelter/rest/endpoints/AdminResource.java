package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddAccountCmd;
import pl.shelter.rest.converters.AdminConverter;
import pl.shelter.rest.managers.AccountService;
import pl.shelter.rest.model.accounts.Admin;
import pl.shelter.rest.utils.ValidationMessages;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/admins")
public class AdminResource {

    private static Logger logger = Logger.getLogger(AdminResource.class.getName());
    private AccountService accountService;
    private HashGenerator hashGenerator;

    @Inject
    public AdminResource(AccountService accountService, HashGenerator hashGenerator) {
        this.accountService = accountService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAllAdmins(){
        return AdminConverter.toDtoFromAdmin(accountService.findAllAdmins());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAdmin(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                    @Valid AddAccountCmd addAccountCmd) {
        addAccountCmd.setPassword(hashGenerator.generateHash(addAccountCmd.getPassword()));
        Admin newAdmin = AdminConverter.fromCreateAccountCmdToAdmin(addAccountCmd);
        return Response.ok().entity(AdminConverter.toDto(accountService.addAdmin(newAdmin))).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto findById(@PathParam("id") UUID id) {
        return AdminConverter.toDto(accountService.findAdminById(id));
    }

}