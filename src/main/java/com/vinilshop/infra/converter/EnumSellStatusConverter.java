package com.vinilshop.infra.converter;

import com.vinilshop.domain.model.EnumSellStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for {@link EnumSellStatus}.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @since 1.0
 */
@Converter(autoApply = true)
public class EnumSellStatusConverter implements AttributeConverter<EnumSellStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumSellStatus enumSellStatus) {
        if (enumSellStatus == null) {
            return null;
        }

        return enumSellStatus.getId();
    }

    @Override
    public EnumSellStatus convertToEntityAttribute(Integer id) {
        if (id != null) {
            return EnumSellStatus.getEnumSellStatusById(id);
        } else {
            return null;
        }
    }
}
