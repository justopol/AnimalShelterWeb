package pl.shelter.rest.exceptions.entities;

import java.util.ResourceBundle;

public class AdopterException extends EntityException{
    public final static String BLACKLIST_ADOPTER = ResourceBundle.getBundle("texts").getString("adopter.blacklisted");
    public final static String  ADOPTER_EXISTS = ResourceBundle.getBundle("texts").getString("adopter.exists");

    public AdopterException(String message) {
        super(message);
    }
    public AdopterException(String message, Throwable cause) {
        super(message, cause);
    }
}