package pl.shelter.rest.exceptions;

import jakarta.ws.rs.core.Response;

public class AdopterException extends AppBaseException {
    public final static String BLACKLIST_ADOPTER = "adopter.blacklisted";
    public final static String  ADOPTER_EXISTS = "adopter.exists";

    public AdopterException(Response.Status status, String key) {
        super(status, key);
    }

    public static AdopterException createForBlacklistAdopter() {
        return new AdopterException(Response.Status.NOT_ACCEPTABLE, BLACKLIST_ADOPTER);
    }
    public static AdopterException createForAdopterExists() {
        return new AdopterException(Response.Status.CONFLICT, ADOPTER_EXISTS);
    }
}
