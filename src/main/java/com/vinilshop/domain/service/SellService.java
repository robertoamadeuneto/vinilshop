package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Sell;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface responsible for all service cases related to the {@link Sell}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface SellService {

    /**
     * Initiates a new {@link Sell}.
     *
     * @return a {@link Sell} object.
     */
    Sell initiate();

    /**
     * Finds all {@link Sell} paginated.
     *
     * @param pageable a {@link Pageable} object.
     * @return a list with {@link Sell} objects.
     */
    Page<Sell> findAll(Pageable pageable);

    /**
     * Finds a {@link Sell} by a given identifier.
     *
     * @param id a {@link Sell} identifier.
     * @return a {@link Sell} object.
     */
    Sell findById(Long id);

    /**
     * Finishes a {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     * @return a {@link Sell} object.
     */
    Sell finish(Sell sell);

    /**
     * Verifies the sell status and throws an exception if it is completed.
     *
     * @param sell a {@link Sell} object.
     */
    void verifySellStatus(Sell sell);

    /**
     * Recalculates the price and the cashback of a {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     */
    void recalculatePriceAndCashback(Sell sell);

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
