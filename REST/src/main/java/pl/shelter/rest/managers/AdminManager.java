package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Admin;
import pl.shelter.rest.repositories.AdminFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdminManager implements  AdminService {

    @Inject
    private AdminFacade adminFacade;
    @Override
    public Admin addAdmin(Admin admin) {
        adminFacade.create(admin);
        return admin;
    }

    @Override
    public Admin findAdminById(UUID id) {
        Optional<Admin> foundAccount = adminFacade.find(id);
        return foundAccount.orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminFacade.findAll();
    }
}
