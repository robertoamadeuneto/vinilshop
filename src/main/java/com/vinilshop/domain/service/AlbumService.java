package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.EnumGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface responsible for all service cases related to the {@link Album}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface AlbumService {

    /**
     * Finds all {@link Album} paginated.
     *
     * @param pageable a {@link Pageable} object.
     * @return a list with all {@link Album} objects.
     */
    Page<Album> findAll(Pageable pageable);

    /**
     * Finds a {@link Album} by a given identifier.
     *
     * @param id the {@link Album} identifier.
     * @return a {@link Album} object.
     */
    Album findById(Long id);

    /**
     * Finds a all {@link Album} by genre paginated and ordering by name ASC.
     *
     * @param genreDescription a {@link String} with the genre description.
     * @param pageable a {@link Pageable} object.
     * @return a list with all {@link Album} objects by {@link EnumGenre}.
     */
    Page<Album> findByGenreOrderByNameAsc(String genreDescription, Pageable pageable);
}
