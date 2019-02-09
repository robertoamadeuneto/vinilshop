package com.vinilshop.infra.converter;

import com.vinilshop.domain.model.EnumSellStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for {@link EnumSellStatus}.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
@Converter(autoApply = true)
public class EnumSellStatusConverter implements AttributeConverter<EnumSellStatus, Integer> {

    /**
     * Converts to a database column.
     *
     * @param enumSellStatus a {@link EnumSellStatus} object.
     * @return an {@link Integer}.
     */
    @Override
    public Integer convertToDatabaseColumn(EnumSellStatus enumSellStatus) {
        if (enumSellStatus == null) {
            return null;
        }

        return enumSellStatus.getId();
    }

    /**
     * Converts to an entity attribute.
     *
     * @param id an {@link Integer} sell status identifier.
     * @return an {@link EnumSellStatus} object.
     */
    @Override
    public EnumSellStatus convertToEntityAttribute(Integer id) {
        if (id != null) {
            return EnumSellStatus.getEnumSellStatusById(id);
        } else {
            return null;
        }
    }
}
