package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Genre} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
