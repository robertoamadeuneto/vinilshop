package com.vinilshop.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class responsible for mapping the Genre entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "cashback_sunday", nullable = false)
    private Long cashbackSunday;

    @Column(name = "cashback_monday", nullable = false)
    private Long cashbackMonday;

    @Column(name = "cashback_thursday", nullable = false)
    private Long cashbackThursday;

    @Column(name = "cashback_wednesday", nullable = false)
    private Long cashbackWednesday;

    @Column(name = "cashback_tuesday", nullable = false)
    private Long cashbackTuesday;

    @Column(name = "cashback_friday", nullable = false)
    private Long cashbackFriday;

    @Column(name = "cashback_saturday", nullable = false)
    private Long cashbackSaturday;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCashbackSunday() {
        return cashbackSunday;
    }

    public void setCashbackSunday(Long cashbackSunday) {
        this.cashbackSunday = cashbackSunday;
    }

    public Long getCashbackMonday() {
        return cashbackMonday;
    }

    public void setCashbackMonday(Long cashbackMonday) {
        this.cashbackMonday = cashbackMonday;
    }

    public Long getCashbackThursday() {
        return cashbackThursday;
    }

    public void setCashbackThursday(Long cashbackThursday) {
        this.cashbackThursday = cashbackThursday;
    }

    public Long getCashbackWednesday() {
        return cashbackWednesday;
    }

    public void setCashbackWednesday(Long cashbackWednesday) {
        this.cashbackWednesday = cashbackWednesday;
    }

    public Long getCashbackTuesday() {
        return cashbackTuesday;
    }

    public void setCashbackTuesday(Long cashbackTuesday) {
        this.cashbackTuesday = cashbackTuesday;
    }

    public Long getCashbackFriday() {
        return cashbackFriday;
    }

    public void setCashbackFriday(Long cashbackFriday) {
        this.cashbackFriday = cashbackFriday;
    }

    public Long getCashbackSaturday() {
        return cashbackSaturday;
    }

    public void setCashbackSaturday(Long cashbackSaturday) {
        this.cashbackSaturday = cashbackSaturday;
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
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name=" + name + ", cashbackSunday=" + cashbackSunday + ", cashbackMonday=" + cashbackMonday + ", cashbackThursday=" + cashbackThursday + ", cashbackWednesday=" + cashbackWednesday + ", cashbackTuesday=" + cashbackTuesday + ", cashbackFriday=" + cashbackFriday + ", cashbackSaturday=" + cashbackSaturday + '}';
    }
}
