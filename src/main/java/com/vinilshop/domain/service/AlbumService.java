package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Album;
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
}
