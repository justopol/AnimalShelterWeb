package pl.shelter.rest.exceptions;

import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.persistence.exceptions.DatabaseException;

public class AppBaseException extends WebApplicationException {

    public static class AppOptimisticLockException extends AppBaseException {
        protected AppOptimisticLockException() {
            super(Response.Status.NOT_FOUND, ERROR_OPTIMISTIC_LOCK);
        }
        protected AppOptimisticLockException(Throwable t) {
            super(Response.Status.NOT_FOUND, ERROR_OPTIMISTIC_LOCK, t);
        }
    }

    public final static String ERROR_UNKNOWN = "ERROR.GENERAL.UNKNOWN";
    protected final static String ERROR_DATABASE = "ERROR.GENERAL.DATABASE";
    protected final static String ERROR_TXRETRYROLLBACK = "ERROR.GENERAL.TXRETRYROLLBACK";
    protected final static String ERROR_PERSISTANCE = "ERROR.GENERAL.PERSISTANCE";
    protected final static String ERROR_ENTITY_NOT_FOUND = "ERROR.GENERAL.ENTITY_NOTFOUND";
    protected final static String ERROR_OPTIMISTIC_LOCK = "ERROR.GENERAL.OPTIMISTIC_LOCK";
    protected final static String ERROR_ACCESS_DENIED = "ERROR.GENERAL.ACCESS_DENY";
    protected final static String ERROR_AUTH_FAILED = "ERROR.GENERAL.AUTH_FAILED";
    protected final static String ERROR_TRANSACTION_ROLLBACK = "ERROR.GENERAL.TRANSACTION_ROLLBACK";

    private Throwable cause;

    @Override
    public synchronized Throwable getCause() { // because the overriden method is synchronized
        return cause;
    }

    protected AppBaseException(Response.Status status, String key, Throwable cause) {
        super(Response.status(status).entity(key).build());
        this.cause = cause;
    }

    public AppBaseException(Response.Status status, String key) {
        super(Response.status(status).entity(key).build());
    }

    public static AppBaseException createForGeneralError() {
        return new AppBaseException(Response.Status.INTERNAL_SERVER_ERROR, ERROR_UNKNOWN);
    }

    public static AppBaseException createForGeneralError(Throwable cause) {
        return new AppBaseException(Response.Status.INTERNAL_SERVER_ERROR, ERROR_UNKNOWN, cause);
    }

    public static AppBaseException createExceptionWithTxRetryRollback() {
        return new AppBaseException(Response.Status.CONFLICT, ERROR_TXRETRYROLLBACK);
    }

    public static AppBaseException createForAuthFailed() {
        return new AppBaseException(Response.Status.UNAUTHORIZED, ERROR_AUTH_FAILED);
    }

    public static AppBaseException createForAccessDenied(Throwable cause) {
        return new AppBaseException(Response.Status.FORBIDDEN, ERROR_ACCESS_DENIED, cause);
    }

    public static AppBaseException createForAccessDenied() {
        return new AppBaseException(Response.Status.FORBIDDEN, ERROR_ACCESS_DENIED);
    }

    public static AppBaseException createForDatabaseError(DatabaseException cause) {
        return new AppBaseException(Response.Status.NOT_ACCEPTABLE, ERROR_DATABASE, cause);
    }

    public static AppBaseException createForPersistenceError(PersistenceException cause) {
        return new AppBaseException(Response.Status.NOT_ACCEPTABLE, ERROR_PERSISTANCE, cause);
    }


    public static AppBaseException createForEntityNotFound(PersistenceException cause) {
        return new AppBaseException(Response.Status.NOT_FOUND, ERROR_ENTITY_NOT_FOUND, cause);
    }

    public static AppBaseException createForEntityNotFound() {
        return new AppBaseException(Response.Status.NOT_FOUND, ERROR_ENTITY_NOT_FOUND);
    }

    public static AppBaseException createForOptimisticLock() {
        return new AppOptimisticLockException();
    }

    public static AppBaseException createForOptimisticLock(OptimisticLockException cause) {
        return new AppOptimisticLockException(cause);
    }

    public static AppBaseException createForTransactionRollback() {
        return new AppBaseException(Response.Status.NOT_MODIFIED, ERROR_TRANSACTION_ROLLBACK);
    }
}
