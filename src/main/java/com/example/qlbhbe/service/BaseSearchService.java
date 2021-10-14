package com.example.qlbhbe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <LI> ListItem DTO type
 * @param <SP> SearchParam type
 */
public interface BaseSearchService<LI, SP> {

    /**
     * Search for a {@link Page} of list items
     *
     * @param params   An object that contains search criteria
     * @param pageable Pagination requirement
     * @return A page of list item
     */
    Page<LI> search(SP params, Pageable pageable);

}
