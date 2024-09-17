package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.adopters.AddAdopterCmd;
import pl.shelter.dto.adopters.AdopterDto;
import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.dto.animals.AnimalDto;
import pl.shelter.rest.converters.AdopterConverter;
import pl.shelter.rest.converters.AnimalConverter;
import pl.shelter.rest.managers.AdopterService;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.animals.Animal;
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
        Adopter newAdopter = AdopterConverter.fromAddAdopterCmd(addAdopterCmd);
        adopterService.addNewAdopter(newAdopter);
    }

}
