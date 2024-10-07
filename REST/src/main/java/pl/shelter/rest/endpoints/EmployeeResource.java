package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.AccountDto;
import pl.shelter.dto.accounts.AddEmployeeCmd;
import pl.shelter.rest.converters.EmployeeConverter;
import pl.shelter.rest.managers.AccountService;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/employees")
public class EmployeeResource {
    private static Logger logger = Logger.getLogger(EmployeeResource.class.getName());

    private AccountService accountService;
    private HashGenerator hashGenerator;

    @Inject
    public EmployeeResource(AccountService accountService, HashGenerator hashGenerator) {
        this.accountService = accountService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @RolesAllowed({"ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAllEmployees() {
        return EmployeeConverter.toDto(accountService.findAllEmployees());
    }
    @GET
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto getEmployeeById(@PathParam("id") UUID id) {
        var employee = accountService.findEmployeeById(id);
        return EmployeeConverter.toDto(employee);
    }
    @POST
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEmployee(AddEmployeeCmd addEmployeeCmd) {
        addEmployeeCmd.setPassword(hashGenerator.generateHash(addEmployeeCmd.getPassword()));
        Employee newEmployee = EmployeeConverter.fromAddEmployeeCmd(addEmployeeCmd);
        accountService.addNewEmployee(newEmployee);
    }

}
