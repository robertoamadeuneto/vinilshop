package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author robertoaneto
 */
public class ArtistMapper {

    @JsonProperty("name")
    private String name;

    public ArtistMapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
