package pl.shelter.rest.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import pl.shelter.rest.managers.AnimalService;
import pl.shelter.rest.model.animals.Animal;
import pl.shelter.rest.model.animals.Mammal;

@Path("/hello-world")
public class HelloResource {
    private AnimalService animalService;

    @Inject
    public HelloResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        Animal mammal = new Mammal();
        mammal.setName("Gofer");
        mammal.setAge(8);

        try {
            animalService.createAnimal(mammal);
        }
        catch (Exception ex){
            System.out.println("error");
        }
        return "Hello, World!";
    }
}