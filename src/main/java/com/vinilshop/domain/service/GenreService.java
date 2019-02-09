package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface responsible for all service cases related to the {@link Genre}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
public interface GenreService {

    /**
     * Finds all {@link Genre}.
     *
     * @param pageable a {@link Pageable} object.
     * @return a list with all {@link Genre} objects.
     */
    Page<Genre> findAll(Pageable pageable);

    /**
     * Finds a {@link Genre} by a given identifier.
     *
     * @param id the {@link Genre} identifier.
     * @return a {@link Genre} object.
     */
    Genre findById(Long id);
}
