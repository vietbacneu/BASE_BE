package com.example.qlbhbe.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @param <D> DTO class type
 * @param <P> Search parameter type
 * @param <E> Entity class type
 */
public abstract class DynamicSearchRepo<D, P, E> {

    private static final Logger logger = LoggerFactory.getLogger(DynamicSearchRepo.class);

    protected EntityManager em;
    protected Class<D> dtoClazz;
    protected Class<E> entityClazz;

    public DynamicSearchRepo(EntityManager em, Class<D> dtoClazz, Class<E> entityClazz) {
        this.em = em;
        this.dtoClazz = dtoClazz;
        this.entityClazz = entityClazz;
    }

    public abstract List<Predicate> getPredicates(CriteriaBuilder cb, Root<E> root, P params);

    public abstract Selection[] getSelections(Root<E> root);

    protected boolean specifyDistinct() {
        return false;
    }

    public Page<D> search(P p, Pageable pageable, boolean shouldCount) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<D> cq = cb.createQuery(dtoClazz);
        Root<E> root = cq.from(entityClazz);


        List<Predicate> predicates = getPredicates(cb, root, p);


        Selection[] selections = getSelections(root);

        cq.select(cb.construct(dtoClazz, selections));

        if (predicates != null && predicates.size() > 0) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        if (pageable != null) {
            cq.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
        }
        if (specifyDistinct()) {
            cq.distinct(true);
        }

        TypedQuery<D> typedQuery = em.createQuery(cq);
        if (pageable != null) {
            typedQuery.setFirstResult((int) pageable.getOffset());
            typedQuery.setMaxResults(pageable.getPageSize());
        }
        List<D> dtoList = typedQuery.getResultList();

        long count = 0;
        if (shouldCount) {
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            countQuery.select(cb.count(countQuery.from(entityClazz)));
            if (predicates != null && predicates.size() > 0) {
                countQuery.where(cb.and(predicates.toArray(new Predicate[0])));
            }
            if (specifyDistinct()) {
                countQuery.distinct(true);
            }
            count = em.createQuery(countQuery).getSingleResult();
        }

        Page<D> dtoPage;
        if (pageable == null || !shouldCount) {
            dtoPage = new PageImpl<>(dtoList);
        } else {
            dtoPage = new PageImpl<D>(dtoList, pageable, count);
        }
        return dtoPage;
    }

    public Page<D> search(P p, Pageable pageable) {
        return search(p, pageable, true);
    }
}
