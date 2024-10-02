package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.EmployeeDto;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeConverter.toDto(accountService.findAllEmployees());
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto getEmployeeById(@PathParam("id") UUID id) {
        var employee = accountService.findEmployeeById(id);
        return EmployeeConverter.toDto(employee);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEmployee(AddEmployeeCmd addEmployeeCmd) {
        addEmployeeCmd.setPassword(hashGenerator.generateHash(addEmployeeCmd.getPassword()));
        Employee newEmployee = EmployeeConverter.fromAddEmployeeCmd(addEmployeeCmd);
        accountService.addNewEmployee(newEmployee);
    }

}
