package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.repositories.AuthFacade;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AuthManager implements AuthService {

    @Inject
    private AuthFacade authFacade;

    public Account authenticate(String login, String hashedPassword) throws AppBaseException {
        return authFacade.findMatchingAndActiveAccount(login, hashedPassword)
                .orElseThrow(AppBaseException::createForAuthFailed);
    }
}