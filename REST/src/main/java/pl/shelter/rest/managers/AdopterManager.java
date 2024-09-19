package pl.shelter.rest.managers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.persistence.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Employee;
import pl.shelter.rest.model.adopters.Address;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adopters.AdopterType;
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

    @Override
    public Adopter findById(UUID id) {
        return adopterFacade.find(id).orElseThrow(AppBaseException::createForEntityNotFound);
    }

    @Override
    public List<Adopter> findAll() {
        return adopterFacade.getAdopters();
    }

    @Override
    public Adopter findByLogin(String login) {
        return null;
    }

    @Override
    public Adopter findSelf() {
        return null;
    }

    @Override
    public void addNewAdopter(Adopter adopter) {
        adopterFacade.create(adopter);
    }

    @Override
    public void editAdopter(UUID uuid, long originalVersion, Adopter adopterModifications) {
        var adopter = findById(uuid);
        editAdopter(originalVersion,adopterModifications,adopter);
        adopterFacade.edit(adopter);
    }

    @Override
    public void editStatusOfAdopter(UUID id, AdopterType adopterType) {

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
            var address = addressFacade.find(modifiedAdopter.getAddress().getId()).orElseThrow(AppBaseException::createForEntityNotFound);;
            editAddress(adopterModifications.getAddress(), address);
            modifiedAdopter.setAddress(address);
        }
    }

    private void editAddress(Address addressModifications, Address modifiedAddress){
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
