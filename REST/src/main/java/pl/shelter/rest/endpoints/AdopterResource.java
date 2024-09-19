package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.ChangePasswordCmd;
import pl.shelter.dto.accounts.EditEmployeeCmd;
import pl.shelter.dto.accounts.adopters.AddAdopterCmd;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adopters.EditAdopterCmd;
import pl.shelter.rest.converters.AdopterConverter;
import pl.shelter.rest.converters.EmployeeConverter;
import pl.shelter.rest.managers.AdopterService;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/adopters")
public class AdopterResource {
    private static Logger logger = Logger.getLogger(AdopterResource.class.getName());

    private AdopterService adopterService;
    private HashGenerator hashGenerator;

    @Inject
    public AdopterResource(AdopterService adopterService, HashGenerator hashGenerator) {
        this.adopterService = adopterService;
        this.hashGenerator = hashGenerator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdopterDto> getAllAdopters() {
        return AdopterConverter.toDto(adopterService.findAll());
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AdopterDto getAdopterById(@PathParam("id") UUID id) {
        var adopter = adopterService.findById(id);
        return AdopterConverter.toDto(adopter);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAdopter(AddAdopterCmd addAdopterCmd) {
        addAdopterCmd.setPassword(hashGenerator.generateHash(addAdopterCmd.getPassword()));
        Adopter newAdopter = AdopterConverter.fromAddAdopterCmd(addAdopterCmd);
        adopterService.addNewAdopter(newAdopter);
    }
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editAdopter(@PathParam("id") UUID id, EditAdopterCmd editAdopterCmd) {
        Adopter newAdopter = AdopterConverter.fromEditAdopterCmd(editAdopterCmd);
        adopterService.editAdopter(id, editAdopterCmd.getOriginalVersion(), newAdopter);
    }

//    @PUT
//    @Path("{id}/password")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void changePassword(@PathParam("id") UUID id, ChangePasswordCmd changePasswordCmd) {
//        employeeService.changePassword(id, changePasswordCmd.getOriginalVersion(), hashGenerator.generateHash(changePasswordCmd.getPassword()));
//    }


}
