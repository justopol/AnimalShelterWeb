package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.adoptions.AddAdoptionCmd;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.rest.converters.AdoptionConverter;
import pl.shelter.rest.managers.AdoptionService;
import pl.shelter.rest.utils.ValidationMessages;

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
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getAllAdoptions(@QueryParam("includeUnderAdoption") boolean includeUnderAdoption,
                                             @QueryParam("includeAdopted") boolean includeAdopted,
                                             @QueryParam("forAdopterId") UUID forAdopterId) {
        return AdoptionConverter.toDto(adoptionService.findAdoptions(includeUnderAdoption,includeAdopted,forAdopterId));
    }

    @GET
    @Path("self")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "ADOPTER"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getSelfAdoptions() {
        return AdoptionConverter.toDto(adoptionService.findSelfAdoptions());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public AdoptionDto createAdoption(@NotNull(message = ValidationMessages.ARGUMENT_NULL)
                                      @Valid AddAdoptionCmd addAdoptionCmd) {
        var adoption = adoptionService.addNewAdoption(addAdoptionCmd.getAdopterUuid(), addAdoptionCmd.getAnimalUuid());
        return AdoptionConverter.toDto(adoption);
    }

    @PUT
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @Path("{id}/finish")
    @Consumes(MediaType.APPLICATION_JSON)
    public void finishAdoption(@PathParam("id") UUID id) {
        adoptionService.finishAdoption(id);
    }

    @PUT
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @Path("{id}/cancel")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cancelAdoption(@PathParam("id") UUID id) {
        adoptionService.cancelAdoption(id);
    }
}
