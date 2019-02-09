package com.vinilshop.infra.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for {@link LocalDateTime}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    /**
     * Converts to a database column.
     *
     * @param locDateTime a {@link LocalDateTime} object.
     * @return a {@link Timestamp}.
     */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    /**
     * Converts to an entity attribute.
     *
     * @param timeStamp an {@link Timestamp} object.
     * @return a {@link LocalDateTime} object.
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timeStamp) {
        return (timeStamp == null ? null : timeStamp.toLocalDateTime());
    }
}
