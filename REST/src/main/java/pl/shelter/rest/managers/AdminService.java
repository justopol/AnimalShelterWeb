package pl.shelter.rest.managers;

import pl.shelter.rest.model.accounts.Admin;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    Admin addAdmin(Admin admin);
    Admin findAdminById(UUID id);
    List<Admin> findAllAdmins();

}
