package pl.shelter.rest.exceptions;

import jakarta.ws.rs.core.Response;

public class AdoptionException extends AppBaseException {
    public static final String ANIMAL_ADOPTED = "animal.already.adopted";
    public static final String ANIMAL_NOT_EXISTS = "animal.not.exist";
    public static final String TIME_EXCEPTION = "end.date.before.start.date";
    public static final String ANIMAL_NOT_READY_FOR_ADOPTION = "animal.not.ready.for.adoption";

    public AdoptionException(Response.Status status, String key) {
        super(status, key);
    }
    public static AdoptionException createForNotReadyForAdoption() {
        return new AdoptionException(Response.Status.NOT_ACCEPTABLE, ANIMAL_NOT_READY_FOR_ADOPTION);
    }
    public static AdoptionException createForAnimalNotExist() {
        return new AdoptionException(Response.Status.NOT_FOUND, ANIMAL_NOT_EXISTS);
    }
    public static AdoptionException createForAnimalAdopted() {
        return new AdoptionException(Response.Status.CONFLICT, ANIMAL_ADOPTED);
    }
    public static AdoptionException createForTimeException() {
        return new AdoptionException(Response.Status.NOT_ACCEPTABLE, TIME_EXCEPTION);
    }
}

