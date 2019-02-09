package com.vinilshop.domain.model;

import com.vinilshop.infra.converter.EnumGenreConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class responsible for mapping the Album entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Entity
@Table(name = "album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist", length = 100, nullable = false)
    private String artist;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "genre_id")
    @Convert(converter = EnumGenreConverter.class)
    private EnumGenre genre;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    public Album() {
    }

    public Album(Long id) {
        this.id = id;
    }

    public Album(Long id, String artist, String name, EnumGenre genre, BigDecimal price) {
        this.id = id;
        this.artist = artist;
        this.name = name;
        this.genre = genre;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumGenre getGenre() {
        return genre;
    }

    public void setGenre(EnumGenre genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", artist=" + artist + ", name=" + name + ", genre=" + genre + ", price=" + price + '}';
    }
}
