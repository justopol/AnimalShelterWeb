package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.adopters.AddAdopterCmd;
import pl.shelter.dto.accounts.adopters.AdopterDto;
import pl.shelter.dto.accounts.adopters.ChangeAdopterStatusCmd;
import pl.shelter.dto.accounts.adopters.EditAdopterCmd;
import pl.shelter.rest.converters.AdopterConverter;
import pl.shelter.rest.managers.AdopterService;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.enums.AdopterType;
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
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdopterDto> getAllAdopters() {
        return AdopterConverter.toDto(adopterService.findAll());
    }
    @GET
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AdopterDto getAdopterById(@PathParam("id") UUID id) {
        var adopter = adopterService.findById(id);
        return AdopterConverter.toDto(adopter);
    }
    @POST
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAdopter(AddAdopterCmd addAdopterCmd) {
        addAdopterCmd.setPassword(hashGenerator.generateHash(addAdopterCmd.getPassword()));
        Adopter newAdopter = AdopterConverter.fromAddAdopterCmd(addAdopterCmd);
        adopterService.addNewAdopter(newAdopter);
    }
    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE","ADOPTER"})
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editAdopter(@PathParam("id") UUID id, EditAdopterCmd editAdopterCmd) {
        Adopter newAdopter = AdopterConverter.fromEditAdopterCmd(editAdopterCmd);
        adopterService.editAdopter(id, editAdopterCmd.getOriginalVersion(), newAdopter);
    }

    @PUT
    @RolesAllowed({"ADMIN"})
    @Path("{id}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeStatus(@PathParam("id") UUID id, ChangeAdopterStatusCmd changeAdopterStatusCmd) {
        AdopterType newAdopterType = AdopterConverter.fromString(changeAdopterStatusCmd.getAdopterType());
        adopterService.editStatusOfAdopter(id, newAdopterType);
    }
}
