package com.vinilshop.domain.model;

import com.vinilshop.infra.converter.EnumSellStatusConverter;
import com.vinilshop.infra.converter.LocalDateConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Class responsible for mapping the Sell entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Entity
@Table(name = "sell")
public class Sell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "cashback", precision = 12, scale = 2)
    private BigDecimal cashback;

    @Column(name = "sell_status_id")
    @Convert(converter = EnumSellStatusConverter.class)
    private EnumSellStatus sellStatus;

    @Column(name = "finished_at")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate finishedAt;

    @OrderBy(value = "id")
    @OneToMany(mappedBy = "sell", fetch = FetchType.LAZY)
    private Collection<SellItem> sellItems;

    public Sell() {
    }

    public Sell(Long id) {
        this.id = id;
    }

    public Sell(BigDecimal price, BigDecimal cashback, EnumSellStatus sellStatus, Collection<SellItem> sellItems) {
        this.price = price;
        this.cashback = cashback;
        this.sellStatus = sellStatus;
        this.sellItems = sellItems;
    }

    public Sell(Long id, BigDecimal price, BigDecimal cashback, EnumSellStatus sellStatus, LocalDate finishedAt, Collection<SellItem> sellItems) {
        this.id = id;
        this.price = price;
        this.cashback = cashback;
        this.sellStatus = sellStatus;
        this.finishedAt = finishedAt;
        this.sellItems = sellItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EnumSellStatus getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(EnumSellStatus sellStatus) {
        this.sellStatus = sellStatus;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Collection<SellItem> getSellItems() {
        return sellItems;
    }

    public void setSellItems(Collection<SellItem> sellItems) {
        this.sellItems = sellItems;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Sell other = (Sell) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sell{" + "id=" + id + ", price=" + price + ", cashback=" + cashback + ", sellStatus=" + sellStatus + ", finishedAt=" + finishedAt + ", sellItems=" + sellItems + '}';
    }
}
