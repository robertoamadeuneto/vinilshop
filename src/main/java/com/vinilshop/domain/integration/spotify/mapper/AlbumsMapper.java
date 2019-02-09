package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * A simple mapper that represents the albums inside the result of the Spotify
 * API's /search endpoint.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
public class AlbumsMapper implements Serializable {

    @JsonProperty("items")
    private List<AlbumMapper> albums;

    public AlbumsMapper() {
    }

    public List<AlbumMapper> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumMapper> albums) {
        this.albums = albums;
    }
}
