package com.vinilshop.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Enumerator representing all the possible status of a sell.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public enum EnumSellStatus implements Serializable {

    PEDING(1),
    COMPLETED(2);

    private final Integer id;

    private EnumSellStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static EnumSellStatus getEnumSellStatusById(Integer id) {
        for (EnumSellStatus value : EnumSellStatus.values()) {
            if (Objects.equals(value.getId(), id)) {
                return value;
            }
        }

        throw new EnumConstantNotPresentException(EnumSellStatus.class, String.valueOf(id));
    }
}
