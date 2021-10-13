package com.example.qlbhbe.repo;

import java.util.Optional;

/**
 * @param <D> DTO type
 * @param <I> ID type
 */
public interface BaseDetailsRepo<D, I> {
    Optional<D> findDetailsDTOById(I id);
}
