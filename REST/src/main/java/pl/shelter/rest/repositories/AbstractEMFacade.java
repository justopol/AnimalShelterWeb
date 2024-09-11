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

    // Modyfikator dostępu wszystkich metod AbstractFacade został zmieniony na protected
    // To powoduje, że każda metoda konkretnej fasady, która ma byc użyta, musi być w niej jawnie nadpisana jako publiczna.
    // To z kolei wymusza zastosowanie dla takiej metody @Transactional, i to jest właśnie cel.
    // Niestety w ten sposób tracimy korzyści ze stosowania typu generycznego. Typowy trade off.

    // Metody wykonujące operacje modyfikujące dane wywołują także metodę flush().
    // To wymusza zrealizowanie odłożonych przez PersistenceContext operacji w ramach jej wywołania
    // Uzywamy tego, aby wymusić rzucenie ewentualnych wyjątków w ramach danej metody fasady
    // co pozwala na ich złapanie


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

    // Ta metoda dodatkowo wykonuje refresh(), czyli wymusza wczytanie z bazy aktualnych danych obiektu
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

