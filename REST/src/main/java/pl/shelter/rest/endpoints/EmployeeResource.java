package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.ChangePasswordCmd;
import pl.shelter.dto.accounts.EmployeeDto;
import pl.shelter.dto.accounts.AddEmployeeCmd;
import pl.shelter.dto.accounts.EditEmployeeCmd;
import pl.shelter.rest.converters.EmployeeConverter;
import pl.shelter.rest.managers.EmployeeService;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/employees")
public class EmployeeResource {
    private static Logger logger = Logger.getLogger(EmployeeResource.class.getName());

    private EmployeeService employeeService;
    private HashGenerator hashGenerator;

    @Inject
    public EmployeeResource(EmployeeService employeeService, HashGenerator hashGenerator) {
        this.employeeService = employeeService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeConverter.toDto(employeeService.findAll());
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDto getEmployeeById(@PathParam("id") UUID id) {
        var employee = employeeService.findById(id);
        return EmployeeConverter.toDto(employee);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEmployee(AddEmployeeCmd addEmployeeCmd) {
        addEmployeeCmd.setPassword(hashGenerator.generateHash(addEmployeeCmd.getPassword()));
        Employee newEmployee = EmployeeConverter.fromAddEmployeeCmd(addEmployeeCmd);
        employeeService.addNewEmployee(newEmployee);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editEmployee(@PathParam("id") UUID id, EditEmployeeCmd editEmployeeCmd) {
        Employee newEmployee = EmployeeConverter.fromEditEmployeeCmd(editEmployeeCmd);
        employeeService.editEmployee(id, editEmployeeCmd.getOriginalVersion(), newEmployee);
    }

    @PUT
    @Path("{id}/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(@PathParam("id") UUID id, ChangePasswordCmd changePasswordCmd) {
        employeeService.changePassword(id, changePasswordCmd.getOriginalVersion(), hashGenerator.generateHash(changePasswordCmd.getPassword()));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathParam("id") UUID id) {
        employeeService.removeEmployee(id);
    }

}
