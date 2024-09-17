package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.adopters.AdopterDto;
import pl.shelter.rest.converters.AdopterConverter;
import pl.shelter.rest.managers.AdopterService;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.utils.security.HashGenerator;

import java.util.List;
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

}
