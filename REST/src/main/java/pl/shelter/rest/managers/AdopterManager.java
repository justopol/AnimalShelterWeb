package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AdopterException;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.adopters.Address;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.enums.AdopterType;
import pl.shelter.rest.repositories.AddressFacade;
import pl.shelter.rest.repositories.AdopterFacade;

import java.util.List;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AdopterManager implements AdopterService {

    @Inject
    private AdopterFacade adopterFacade;
    @Inject
    private AddressFacade addressFacade;
    @Inject
    private SecurityContext sctx;


    @Override
    public Adopter findById(UUID id) {
        return adopterFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Adopter> findAll() {
        return adopterFacade.getAdopters();
    }

    @Override
    public void addNewAdopter(Adopter adopter) {
        try {
            adopterFacade.create(adopter);
        } catch(PersistenceException persistenceException) {
            throw AdopterException.createForAdopterExists();
        }

    }

    @Override
    public void editAdopter(UUID uuid, long originalVersion, Adopter adopterModifications) {
        var adopter = findById(uuid);
        editAdopter(originalVersion, adopterModifications, adopter);
        adopterFacade.edit(adopter);
    }

    @Override
    public void editStatusOfAdopter(UUID id, AdopterType adopterType) {
        var adopter = findById(id);
        adopter.setAdopterType(adopterType);
        adopterFacade.edit(adopter);
    }

    @Override
    public void changePassword(UUID uuid, long originalVersion, String password) {
        var adopter = findById(uuid);
        if (originalVersion != adopter.getVersion()) throw AppBaseException.createForOptimisticLock();
        adopter.setPassword(password);
        adopterFacade.edit(adopter);
    }
    @Override
    public Adopter findAdopterSelf() {
        return findAdopterByLogin(sctx.getCallerPrincipal().getName());
    }
    @Override
    public Adopter findAdopterByLogin(String login) {
        return adopterFacade.findByLogin(login).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    private void editAdopter(long originalVersion, Adopter adopterModifications, Adopter modifiedAdopter) {
        if (originalVersion != modifiedAdopter.getVersion())
            throw AppBaseException.createForOptimisticLock();

        if (null != adopterModifications.getFirstName()) {
            modifiedAdopter.setFirstName(adopterModifications.getFirstName());
        }
        if (null != adopterModifications.getLastName()) {
            modifiedAdopter.setLastName(adopterModifications.getLastName());
        }
        if (null != adopterModifications.getEmail()) {
            modifiedAdopter.setEmail(adopterModifications.getEmail());
        }
        if (null != adopterModifications.getAddress()) {
            var address = addressFacade.find(modifiedAdopter.getAddress().getId()).orElseThrow(AppBaseException::createForEntityNotFound);
            editAddress(adopterModifications.getAddress(), address);
            modifiedAdopter.setAddress(address);
        }
    }

    private void editAddress(Address addressModifications, Address modifiedAddress) {
        if (null != addressModifications.getStreetName()) {
            modifiedAddress.setStreetName(addressModifications.getStreetName());
        }
        if (null != addressModifications.getStreetNumber()) {
            modifiedAddress.setStreetNumber(addressModifications.getStreetNumber());
        }
        if (null != addressModifications.getCity()) {
            modifiedAddress.setCity(addressModifications.getCity());
        }
    }
}
