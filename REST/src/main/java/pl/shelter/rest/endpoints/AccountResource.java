package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.EditAccountCmd;
import pl.shelter.dto.auth.ChangePasswordDto;
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
    @GET
    @RolesAllowed({"ADMIN"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto getAccountById(@PathParam("id") UUID id) {
        var res = AccountConverter.toDto(accountService.findById(id));
        return res;
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
    public void changePassword(@PathParam("id") UUID id, ChangePasswordDto changePasswordDto) {
        accountService.changePassword(id, hashGenerator.generateHash(changePasswordDto.getNewPassword()));
    }

    @POST
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}/activate")
    public void activateAccount(@PathParam("id") UUID id) {
        accountService.activateAccount(id);
    }
    @POST
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
