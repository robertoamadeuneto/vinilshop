package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.EnumGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Album} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * Finds all {@link Album} by genre paginated and ordering by name ASC.
     *
     * @param enumGenre a {@link EnumGenre} object.
     * @param pageable a {@link Pageable} object.
     * @return a list with all {@link Album} objects by {@link EnumGenre}.
     */
    Page<Album> findByGenreOrderByNameAsc(EnumGenre enumGenre, Pageable pageable);
}
