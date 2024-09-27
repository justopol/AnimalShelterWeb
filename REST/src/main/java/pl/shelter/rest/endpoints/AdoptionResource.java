package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.shelter.dto.accounts.adoptions.AddAdoptionCmd;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.rest.converters.AdoptionConverter;
import pl.shelter.rest.exceptions.entities.AdoptionException;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.managers.AdoptionService;
import pl.shelter.rest.model.adoptions.Adoption;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getAllAdoptions() {
        return AdoptionConverter.toDto(adoptionService.findAll());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAdoption(AddAdoptionCmd addAdoptionCmd) {
        try{
            adoptionService.addNewAdoption(addAdoptionCmd.getAdopterUuid(), addAdoptionCmd.getAnimalUuid());
        }
        catch (AdoptionException ex){
            throw new AppBaseException(Response.Status.FORBIDDEN, ex.getMessage());
        }
    }

    @PUT
    @Path("{id}/finish")
    @Consumes(MediaType.APPLICATION_JSON)
    public void finishAdoption(@PathParam("id") UUID id) {
        //todo
        adoptionService.finishAdoption(id);
    }

    @PUT
    @Path("{id}/cancel")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cancelAdoption(@PathParam("id") UUID id) {
        //todo
        adoptionService.cancelAdoption(id);
    }
}
