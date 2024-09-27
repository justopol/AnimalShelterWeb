package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class EntityException extends Exception{
    public EntityException(final String message) {
        super(message);
    }
    public EntityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

