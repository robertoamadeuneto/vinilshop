package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple mapper that represents the artists inside the result of the Spotify
 * API's /search endpoint.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
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
