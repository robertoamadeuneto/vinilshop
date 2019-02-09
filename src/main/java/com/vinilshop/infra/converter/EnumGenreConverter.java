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

    @Override
    public Integer convertToDatabaseColumn(EnumGenre enumGenre) {
        if (enumGenre == null) {
            return null;
        }

        return enumGenre.getId();
    }

    @Override
    public EnumGenre convertToEntityAttribute(Integer id) {
        if (id != null) {
            return EnumGenre.getEnumGenreById(id);
        } else {
            return null;
        }
    }
}
