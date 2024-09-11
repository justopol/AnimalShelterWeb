package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import pl.shelter.rest.managers.AnimalService;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;

import java.util.List;

@Path("/animals")
public class AnimalResource {
    private AnimalService animalService;

    @Inject
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @POST
    @Produces("text/plain")
    public String createAnimal() {
        Animal mammal = new Mammal();
        mammal.setName("Lucky");
        mammal.setAge(10);

        try {
            animalService.createAnimal(mammal);
        } catch (Exception ex) {
            System.out.println("error");
        }
        return "Hello, World!";
    }

    @GET
    @Produces("text/plain")
    public String getAnimal() {
        try {
            List<Animal> list = animalService.getAnimals();
            int a = 9;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return "Hello, World!";
    }
}