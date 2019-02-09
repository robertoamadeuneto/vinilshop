package com.vinilshop.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Enumerator responsible for mapping the albums genres.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public enum EnumGenre implements Serializable {

    POP(1, "Pop"),
    MPB(2, "MPB"),
    CLASSIC(3, "Classic"),
    ROCK(4, "Rock");

    private final Integer id;
    private final String description;

    private EnumGenre(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static EnumGenre getEnumGenreById(Integer id) {
        for (EnumGenre value : EnumGenre.values()) {
            if (Objects.equals(value.getId(), id)) {
                return value;
            }
        }

        throw new EnumConstantNotPresentException(EnumGenre.class, String.valueOf(id));
    }

    public static EnumGenre getEnumGenreByDescription(String description) {
        for (EnumGenre value : EnumGenre.values()) {
            if (value.getDescription().trim().equalsIgnoreCase(description.trim())) {
                return value;
            }
        }

        throw new EnumConstantNotPresentException(EnumGenre.class, description);
    }
}
