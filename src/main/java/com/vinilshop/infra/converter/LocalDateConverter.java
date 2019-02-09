package com.vinilshop.infra.converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for {@link LocalDate}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

    /**
     * Converts to a database column.
     *
     * @param localDate a {@link LocalDateTime} object.
     * @return a {@link Timestamp}.
     */
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {
        return localDate != null ? Timestamp.valueOf(localDate.atStartOfDay()) : null;
    }

    /**
     * Converts to an entity attribute.
     *
     * @param timeStamp an {@link Timestamp} object.
     * @return a {@link LocalDateTime} object.
     */
    @Override
    public LocalDate convertToEntityAttribute(Timestamp timeStamp) {
        return timeStamp != null ? timeStamp.toLocalDateTime().toLocalDate() : null;
    }
}
