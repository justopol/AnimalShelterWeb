package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.adoptions.AddAdoptionCmd;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.rest.converters.AdoptionConverter;
import pl.shelter.rest.managers.AdoptionService;

import java.util.List;
import java.util.UUID;

@Path("/adoptions")
public class AdoptionResource {
    private final AdoptionService adoptionService;

    @Inject
    public AdoptionResource(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GET
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getAllAdoptions() {
        return AdoptionConverter.toDto(adoptionService.findAll());
    }
    @GET
    @Path("self")
   // @RolesAllowed({"ADMIN","EMPLOYEE","ADOPTER"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getSelfAdoptions() {
        return AdoptionConverter.toDto(adoptionService.findSelfAdoptions());
    }

    @POST
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAdoption(AddAdoptionCmd addAdoptionCmd) {
        adoptionService.addNewAdoption(addAdoptionCmd.getAdopterUuid(), addAdoptionCmd.getAnimalUuid());

    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}/finish")
    @Consumes(MediaType.APPLICATION_JSON)
    public void finishAdoption(@PathParam("id") UUID id) {
        adoptionService.finishAdoption(id);
    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}/cancel")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cancelAdoption(@PathParam("id") UUID id) {
        adoptionService.cancelAdoption(id);
    }
}
