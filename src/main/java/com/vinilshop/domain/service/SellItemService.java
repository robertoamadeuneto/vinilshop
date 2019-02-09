package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import java.util.Collection;

/**
 * Interface responsible for all service cases related to the {@link SellItem}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface SellItemService {

    /**
     * Finds all {@link SellItem} by its {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     * @return a list with {@link SellItem} objects.
     */
    Collection<SellItem> findBySell(Sell sell);

    /**
     * Finds a {@link SellItem} by its {@link Sell} and identifier.
     *
     * @param sell a {@link Sell} objects.
     * @param id the {@link SellItem} identifier.
     * @return a list with {@link SellItem} objects.
     */
    SellItem findBySellAndId(Sell sell, Long id);

    /**
     * Saves a new {@link SellItem} on a {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     * @param sellItem a {@link SellItem} object.
     * @return a {@link SellItem} object.
     */
    SellItem add(Sell sell, SellItem sellItem);

    /**
     * Removes a {@link SellItem} from a {@link Sell}.
     *
     * @param sell a {@link Sell} object.
     * @param id a {@link SellItem} identifier.
     */
    void remove(Sell sell, Long id);
}
