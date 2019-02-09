package com.vinilshop.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class responsible for mapping the SellItem entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2012
 * @version 1.0
 */
@Entity
@Table(name = "sell_item")
public class SellItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id", referencedColumnName = "id")
    private Sell sell;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "cashback", precision = 12, scale = 2)
    private BigDecimal cashback;

    public SellItem() {
    }

    public SellItem(Long id, Sell sell, Album album, BigDecimal price, BigDecimal cashback) {
        this.id = id;
        this.sell = sell;
        this.album = album;
        this.price = price;
        this.cashback = cashback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCashback() {
        return cashback;
    }

    public void setCashback(BigDecimal cashback) {
        this.cashback = cashback;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final SellItem other = (SellItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SellItem{" + "id=" + id + ", sell=" + sell + ", album=" + album + ", price=" + price + ", cashback=" + cashback + '}';
    }
}
