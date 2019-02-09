package com.vinilshop.domain.integration.spotify.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author robertoaneto
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
