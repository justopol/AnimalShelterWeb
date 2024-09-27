package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class AdoptionException extends EntityException {
    public static final String ANIMAL_ADOPTED = "animal.already.adopted";
    public static final String ANIMAL_NOT_EXISTS = "animal.not.exist";
    public static final String TIME_EXCEPTION = "end.date.before.start.date";
    public static final String ANIMAL_NOT_READY_FOR_ADOPTION = "animal.not.ready.for.adoption";

    public AdoptionException(String message) {
        super(message);
    }

    public AdoptionException(String message, Throwable cause) {
        super(message, cause);
    }
}

