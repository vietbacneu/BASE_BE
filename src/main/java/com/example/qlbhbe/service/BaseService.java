package com.example.qlbhbe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Base service that focuses on CRUD and retrieving ListItem and Details DTOs
 *
 * @param <E>  Entity type
 * @param <LI> ListItem DTO type
 * @param <DT> Details DTO type
 * @param <SP> SearchParams type
 * @param <I>  ID type
 */
//public interface BaseService<E extends Serializable, LI, DT, SP, I extends Serializable> {
public interface BaseService<E extends Serializable, I extends Serializable> {

    ///**
    // * Search for a {@link Page} of list items
    // * @param params An object that contains search criteria
    // * @param pageable Pagination requirement
    // * @return A page of list item
    // */
    //Page<LI> search(SP params, Pageable pageable);


    /**
     * Find a details DTO
     *
     * @param id id of the desired object
     * @return The details DTO
     */
    //Optional<DT> findDetailsDTOById(I id);

    boolean existsById(I id);

    void save(E entity);

    void delete(E entity);

    void deleteById(I id);

    Optional<E> findById(I id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E getOne(I id);
}
