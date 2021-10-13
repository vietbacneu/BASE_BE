package com.example.qlbhbe.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.Optional;

public abstract class AbstractDetailsRepo<D, E, I> implements BaseDetailsRepo<D, I> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDetailsRepo.class);

    private EntityManager em;
    private Class<D> dtoClazz;
    private Class<E> entityClazz;

    public AbstractDetailsRepo(EntityManager em, Class<D> dtoClazz, Class<E> entityClazz) {
        this.em = em;
        this.dtoClazz = dtoClazz;
        this.entityClazz = entityClazz;
    }

    @Override
    public Optional<D> findDetailsDTOById(I id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<D> cq = cb.createQuery(dtoClazz);
        Root<E> root = cq.from(entityClazz);

        Selection[] selections = getDetailsSelections(root);
        cq.select(cb.construct(dtoClazz, selections));
        cq.where(cb.equal(root.get("id"), id));
        TypedQuery<D> typedQuery = em.createQuery(cq);

        Optional<D> result = Optional.empty();
        try {
            D dto = typedQuery.getSingleResult();
            return Optional.of(dto);
        } catch (NoResultException nre) {
            logger.error("No result found for entity class = " + entityClazz.getName()
                    + " and ID = " + id, nre);
        }

        return result;

    }


    abstract public Selection[] getDetailsSelections(Root<E> root);


    public EntityManager getEm() {
        return em;
    }

    public Class<D> getDtoClazz() {
        return dtoClazz;
    }

    public Class<E> getEntityClazz() {
        return entityClazz;
    }
}
