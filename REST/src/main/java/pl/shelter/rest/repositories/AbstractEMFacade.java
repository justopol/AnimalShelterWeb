package pl.shelter.rest.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractEMFacade<T> {

    private Class<T> entityClass;

    public AbstractEMFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();



    protected void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    protected void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    protected void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }

    protected Optional<T> find(UUID id) {
        return Optional.ofNullable(getEntityManager().find(entityClass, id));
    }


    protected Optional<T> findAndRefresh(UUID id) {
        Optional<T> entity = find(id);
        if(entity.isPresent()) {
            getEntityManager().refresh(entity.get());
        }
        return entity;
    }

    protected List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    protected int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected void forceVersionIncrement(T entity) {
        getEntityManager().lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}

