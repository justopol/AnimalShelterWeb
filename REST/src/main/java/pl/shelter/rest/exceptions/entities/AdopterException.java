package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class AdopterException extends EntityException{
    public final static String BLACKLIST_ADOPTER = "adopter.blacklisted";
    public final static String  ADOPTER_EXISTS = "adopter.exists";

    public AdopterException(String message) {
        super(message);
    }
    public AdopterException(String message, Throwable cause) {
        super(message, cause);
    }
}
