package pl.shelter.rest.managers;

import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.model.accounts.Account;

public interface AuthService {
    Account authenticate(String login, String password) throws AppBaseException;
}

