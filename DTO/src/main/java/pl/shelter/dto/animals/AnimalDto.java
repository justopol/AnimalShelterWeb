package pl.shelter.dto.animals;


import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import pl.shelter.dto.AbstractDto;
import java.util.UUID;

public abstract class AnimalDto extends AbstractDto {

    private String type;


    private boolean available;
    private String name;
    private int age;
    private double adoptionPrice;
    protected String adoptionStatus;

    public AnimalDto() {
    }

//    @JsonbCreator
//    public AnimalDto(@JsonbProperty("id") UUID id,
//                     @JsonbProperty("version") long version,
//                     @JsonbProperty("type") String type,
//                     @JsonbProperty("available") boolean available,
//                     @JsonbProperty("name") String name,
//                     @JsonbProperty("age") int age,
//                     @JsonbProperty("adoptionPrice") double adoptionPrice,
//                     @JsonbProperty("adoptionStatus") String adoptionStatus) {

public AnimalDto(UUID id, long version, String type, boolean available, String name, int age, double adoptionPrice, String adoptionStatus) {
        super(id, version);
        this.type = type;
        this.available = available;
        this.name = name;
        this.age = age;
        this.adoptionPrice = adoptionPrice;
        this.adoptionStatus = adoptionStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAdoptionPrice() {
        return adoptionPrice;
    }

    public void setAdoptionPrice(double adoptionPrice) {
        this.adoptionPrice = adoptionPrice;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }
    public String getAnimalId() {
        return getId().toString();
    }

}
