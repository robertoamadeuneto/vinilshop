package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A simple mapper that represents the album inside the result of the Spotify
 * API's /search endpoint.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
public class AlbumMapper {

    @JsonProperty("name")
    private String name;

    @JsonProperty("artists")
    private List<ArtistMapper> artists;

    public AlbumMapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArtistMapper> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistMapper> artists) {
        this.artists = artists;
    }
}
