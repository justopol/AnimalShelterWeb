package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class EntityException extends Exception{

    public static final String NOT_FOUND = ResourceBundle.getBundle("texts").getString("not.found");
    public static final String ENTITY_EXISTS = ResourceBundle.getBundle("texts").getString("entity.exists");


    public EntityException(final String message) {
        super(message);
    }
    public EntityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

