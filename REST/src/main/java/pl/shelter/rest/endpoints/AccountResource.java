package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.ChangePasswordCmd;
import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.rest.converters.AccountConverter;
import pl.shelter.rest.managers.AccountService;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/accounts")
public class AccountResource {

    private static Logger logger = Logger.getLogger(AccountResource.class.getName());
    private AccountService accountService;
    private HashGenerator hashGenerator;

    @Inject
    public AccountResource(AccountService accountService, HashGenerator hashGenerator) {
        this.accountService = accountService;
        this.hashGenerator = hashGenerator;
    }
    @GET
    @RolesAllowed({"ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAllAccounts() {
        return AccountConverter.toDto(accountService.findAllAccounts());
    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE","ADOPTER"})
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editAccount(@PathParam("id") UUID id, EditAccountCmd editAccountCmd) {
        Account newAccount = AccountConverter.fromEditAccountCmd(editAccountCmd);
        accountService.editAccount(id, editAccountCmd.getOriginalVersion(), newAccount);
    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE","ADOPTER"})
    @Path("{id}/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(@PathParam("id") UUID id, ChangePasswordCmd changePasswordCmd) {
        accountService.changePassword(id, changePasswordCmd.getOriginalVersion(), hashGenerator.generateHash(changePasswordCmd.getPassword()));
    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}/activate")
    public void activateAccount(@PathParam("id") UUID id) {
        accountService.activateAccount(id);
    }
    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}/deactivate")
    public void deactivateAccount(@PathParam("id") UUID id) {
        accountService.deactivateAccount(id);
    }

    @DELETE
    @RolesAllowed({"ADMIN"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAccount(@PathParam("id") UUID id) {
        accountService.removeAccount(id);
    }

}
