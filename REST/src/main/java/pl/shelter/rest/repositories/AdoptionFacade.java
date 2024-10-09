package pl.shelter.rest.repositories;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import pl.shelter.rest.exceptions.AppBaseException;
import pl.shelter.rest.interceptor.TxTracked;
import pl.shelter.rest.model.accounts.Account;
import pl.shelter.rest.model.adopters.Adopter;
import pl.shelter.rest.model.adoptions.Adoption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TxTracked
@Transactional(Transactional.TxType.MANDATORY)
public class AdoptionFacade extends AbstractEMFacade<Adoption> {
    @PersistenceContext
    private EntityManager em;

    public AdoptionFacade() {
        super(Adoption.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public List<Adoption> getAdoptions() {
        return super.findAll();
    }
    public void create(Adoption adoption) {
        super.create(adoption);
    }
    @Override
    public Optional<Adoption> find(UUID id) {
        return super.find(id);
    }
    @Override
    public void edit(Adoption entity) {
        try {
            super.edit(entity);
        } catch (OptimisticLockException ole) {
            throw AppBaseException.createForOptimisticLock(ole);
        } catch (PersistenceException pe) {
            throw AppBaseException.createForPersistenceError(pe);
        }
    }
    public List<Adoption> findByLogin(String login) {
        TypedQuery<Adoption> tq = em.createNamedQuery("Adoption.findByLogin", Adoption.class);
        tq.setParameter("login", login);
        return tq.getResultList();
    }

    @Override
    public void remove(Adoption entity){
        super.remove(entity);
    }

    public List<Adoption> matchAdoptions(boolean includeUnderAdoption, boolean includeAdopted, Adopter forAdopter) {

        if (!includeUnderAdoption && !includeAdopted) return new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Adoption> query = cb.createQuery(Adoption.class);
        Root<Adoption> from = query.from(Adoption.class);
        query = query.select(from).orderBy(cb.desc(from.get("startadoptiontime")));
        Predicate criteria = cb.conjunction();

        if (null != forAdopter) {
            criteria = cb.and(criteria, cb.equal(from.get("adopter_id"), forAdopter.getId()));
        }
        if(!includeAdopted || !includeUnderAdoption)
            if(includeAdopted)
                criteria = cb.and(criteria, cb.isNotNull(from.get("endadoptiontime")));
            else
                criteria = cb.and(criteria, cb.isNull(from.get("endadoptiontime")));

        query = query.where(criteria);
        TypedQuery<Adoption> tq = em.createQuery(query);
        return tq.getResultList();
    }

}
