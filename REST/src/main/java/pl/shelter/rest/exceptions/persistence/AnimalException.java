package pl.shelter.rest.exceptions.persistence;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;

public class AnimalException extends AppBaseException {

    private static final String ERROR_ANIMAL_ALREADY_EXIST = "ERROR.ANIMAL.ALREADY_EXIST";

    public AnimalException(Response.Status status, String key, Throwable cause) {
        super(status, key, cause);
    }

    public AnimalException(Response.Status status, String key) {
        super(status, key);
    }

    public static AnimalException createForAnimalAlreadyExist(PersistenceException pe) {
        return new AnimalException(Response.Status.NOT_ACCEPTABLE, ERROR_ANIMAL_ALREADY_EXIST, pe);
    }


}