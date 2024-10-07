package pl.shelter.rest.endpoints;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import pl.shelter.dto.animals.*;
import pl.shelter.rest.converters.AnimalConverter;
import pl.shelter.rest.managers.AnimalService;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;
import pl.shelter.rest.model.animals.Reptile;

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
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("mammal")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMammal(AddMammalCmd addMammalCmd) {
       Animal newMammal = AnimalConverter.fromAddMammalCmd(addMammalCmd);
        animalService.createAnimal(newMammal);
    }

    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("mammal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMammal(@PathParam("id") UUID id,
                             EditMammalCmd editMammalCmd) {
        Mammal MammalModifications = AnimalConverter.fromEditMammalCmd(editMammalCmd);
        animalService.editMammalById(id, editMammalCmd.getOriginalVersion(), MammalModifications);
    }

    @POST
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("reptile")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createReptile(AddReptileCmd addReptileCmd) {
        Animal newReptile = AnimalConverter.fromAddReptileCmd(addReptileCmd);
        animalService.createAnimal(newReptile);
    }

    @GET
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnimalDto> getAnimal() {
        return AnimalConverter.toDto(animalService.getAnimals());
    }

    @GET
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnimalDto getAnimalById(@PathParam("id") UUID id) {
        var animal = animalService.findAnimalById(id);
        return AnimalConverter.getAnimalDto(animal);
    }
    @PUT
    @RolesAllowed({"ADMIN","EMPLOYEE"})
    @Path("reptile/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateReptile(@PathParam("id") UUID id,
                             EditReptileCmd editReptileCmd) {
        Reptile ReptileModifications = AnimalConverter.fromEditReptileCmd(editReptileCmd);
        animalService.editReptileById(id, editReptileCmd.getOriginalVersion(), ReptileModifications);
    }
}