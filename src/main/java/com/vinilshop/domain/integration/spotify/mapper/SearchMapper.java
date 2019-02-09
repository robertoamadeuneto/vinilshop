package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * A simple POJO that represents the result of the Spotify API's /search
 * endpoint.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
public class SearchMapper implements Serializable {

    @JsonProperty("albums")
    private AlbumsMapper albums;

    public SearchMapper() {
    }

    public AlbumsMapper getAlbums() {
        return albums;
    }

    public void setAlbums(AlbumsMapper albums) {
        this.albums = albums;
    }
}
