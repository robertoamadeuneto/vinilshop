package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author robertoaneto
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
