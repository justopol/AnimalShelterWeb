package pl.shelter.rest.exceptions;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;

public class AccountException extends AppBaseException {

    private static final String ERROR_ACCOUNT_LOGIN_EXIST = "ERROR.ACCOUNT.LOGIN_EXIST";
    private static final String ERROR_ACCOUNT_EMAIL_EXIST = "ERROR.ACCOUNT.EMAIL_EXIST";
    private static final String ERROR_ACCOUNT_PERSONALID_EXIST = "ERROR.ACCOUNT.PERSONALID_EXIST";
    private static final String ERROR_ACCOUNT_OLD_PASSWORD_MISMATCH = "ERROR.ACCOUNT.OLD_PASSWORD_MISMATCH";

    public AccountException(Response.Status status, String key, Throwable cause) {
        super(status, key, cause);
    }

    public AccountException(Response.Status status, String key) {
        super(status, key);
    }

    public static AccountException createForLoginExist(PersistenceException pe) {
        return new AccountException(Response.Status.NOT_ACCEPTABLE, ERROR_ACCOUNT_LOGIN_EXIST, pe);
    }

    public static AccountException createForEmailExist(PersistenceException pe) {
        return new AccountException(Response.Status.NOT_ACCEPTABLE, ERROR_ACCOUNT_EMAIL_EXIST, pe);
    }

    public static AccountException createForPersonalIdExist(PersistenceException pe) {
        return new AccountException(Response.Status.NOT_ACCEPTABLE, ERROR_ACCOUNT_PERSONALID_EXIST, pe);
    }

    public static AccountException createForOldPasswordMismatch() {
        return new AccountException(Response.Status.UNAUTHORIZED, ERROR_ACCOUNT_OLD_PASSWORD_MISMATCH);
    }
}
