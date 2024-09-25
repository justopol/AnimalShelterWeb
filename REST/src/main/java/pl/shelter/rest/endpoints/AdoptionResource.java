package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.accounts.adoptions.AdoptionDto;
import pl.shelter.rest.converters.AdoptionConverter;
import pl.shelter.rest.managers.AdoptionService;

import java.util.List;

@Path("/adoptions")
public class AdoptionResource {
    private AdoptionService adoptionService;

    @Inject
    public AdoptionResource(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdoptionDto> getAllAdoptions() {
        var res = adoptionService.findAll();

        for (var item : res){
            var r = AdoptionConverter.toDto(item);
            int a = 5;
        }

        return AdoptionConverter.toDto(adoptionService.findAll());
    }
}
