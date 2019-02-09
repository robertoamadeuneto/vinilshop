package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Album;
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

}
