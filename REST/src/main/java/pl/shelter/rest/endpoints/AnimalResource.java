package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.animals.AddMammalCmd;
import pl.shelter.dto.animals.EditMammalCmd;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String createMammal(AddMammalCmd addMammalCmd) {
       Animal newMammal = AnimalConverter.fromAddMammalCmd(addMammalCmd);

        try {
            animalService.createAnimal(newMammal);
        } catch (Exception ex) {
            System.out.println("error");
        }
        return "post:Hello, World!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> getAnimal() {
        try {
            return animalService.getAnimals();
        } catch (Exception ex) {
            System.out.println("error");
        }
        return null;
    }
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAnimal(@PathParam("id") UUID id,
                             EditMammalCmd editMammalCmd) {
        Mammal MammalModifications = AnimalConverter.fromEditMammalCmd(editMammalCmd);
        animalService.editMammalById(id, editMammalCmd.getOriginalVersion(), MammalModifications);
    }
}