package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link SellItem} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface SellItemRepository extends JpaRepository<SellItem, Long> {

    /**
     * Finds all {@link SellItem} on database by its {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     * @return a list with {@link SellItem} objects.
     */
    Collection<SellItem> findBySell(Sell sell);

    /**
     * Finds a {@link SellItem} on database by its {@link Sell} and identifier.
     *
     * @param sell a {@link Sell} object.
     * @param id the {@link SellItem} identifier.
     * @return a list with {@link SellItem} objects.
     */
    SellItem findBySellAndId(Sell sell, Long id);
}
