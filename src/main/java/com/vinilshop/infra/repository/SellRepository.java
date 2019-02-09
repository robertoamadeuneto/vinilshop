package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Sell;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Sell} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface SellRepository extends JpaRepository<Sell, Long> {

    /**
     * Finds all {@link Sell} by finished at range and paginated.
     *
     * @param initialDate a {@link LocalDate} with the initial date.
     * @param finalDate a {@link FinalDateTime} with the final date.
     * @param pageable a {@link Pageable} object.
     * @return a list with {@link Sell} objects.
     */
    Page<Sell> findByFinishedAtBetween(LocalDate initialDate, LocalDate finalDate, Pageable pageable);
}
