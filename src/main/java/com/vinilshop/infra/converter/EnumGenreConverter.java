package com.vinilshop.infra.converter;

import com.vinilshop.domain.model.EnumGenre;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for {@link EnumGenre}.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
@Converter(autoApply = true)
public class EnumGenreConverter implements AttributeConverter<EnumGenre, Integer> {

    /**
     * Converts to a database column.
     *
     * @param enumGenre a {@link EnumGenre} object.
     * @return an {@link Integer}.
     */
    @Override
    public Integer convertToDatabaseColumn(EnumGenre enumGenre) {
        if (enumGenre == null) {
            return null;
        }
        return enumGenre.getId();
    }

    /**
     * Converts to an entity attribute.
     *
     * @param id an {@link Integer} genre identifier.
     * @return an {@link EnumGenre} object.
     */
    @Override
    public EnumGenre convertToEntityAttribute(Integer id) {
        if (id != null) {
            return EnumGenre.getEnumGenreById(id);
        } else {
            return null;
        }
    }
}
