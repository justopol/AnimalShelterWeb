package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class AdoptionException extends EntityException {
    public static final String ANIMAL_ADOPTED = ResourceBundle.getBundle("texts").getString("animal.already.adopted");
    public static final String ANIMAL_NOT_EXISTS = ResourceBundle.getBundle("texts").getString("animal.not.exist");
    public static final String TIME_EXCEPTION = ResourceBundle.getBundle("texts").getString("end.date.before.start.date");

    public AdoptionException(String message) {
        super(message);
    }

    public AdoptionException(String message, Throwable cause) {
        super(message, cause);
    }
}

