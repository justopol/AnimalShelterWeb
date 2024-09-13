package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.dto.animals.EditMammalCmd;
import pl.shelter.dto.animals.MammalDto;
import pl.shelter.rest.converters.AnimalConverter;
import pl.shelter.rest.managers.AnimalService;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;

import java.util.List;
import java.util.UUID;

@Path("/animals")
public class AnimalResource {
    private AnimalService animalService;

    @Inject
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @POST
    @Path("mammal")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMammal(AddMammalCmd addMammalCmd) {
       Animal newMammal = AnimalConverter.fromAddMammalCmd(addMammalCmd);
        try {
            animalService.createAnimal(newMammal);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    @GET
    @Path("mammal")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MammalDto> getAnimal() {
       return AnimalConverter.toDto(animalService.getAnimals());

    }
    @PUT
    @Path("mammal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAnimal(@PathParam("id") UUID id,
                             EditMammalCmd editMammalCmd) {
        Mammal MammalModifications = AnimalConverter.fromEditMammalCmd(editMammalCmd);
        animalService.editMammalById(id, editMammalCmd.getOriginalVersion(), MammalModifications);
    }
}