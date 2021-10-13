package com.example.qlbhbe.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Base Repository for list view
 *
 * @param <D> List item DTO
 * @param <P> Parameter
 */
public interface BaseSearchRepo<D, P> {
    Page<D> search(P params, Pageable pageable);

    Page<D> search(P p, Pageable pageable, boolean shouldCount);
}
