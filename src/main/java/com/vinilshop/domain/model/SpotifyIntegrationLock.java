package com.vinilshop.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class responsible for mapping the Spotify Integration Lock entity.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
@Entity
@Table(name = "spotify_integration_lock")
public class SpotifyIntegrationLock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "integrated")
    private Boolean integrated;

    @Column(name = "integrated_at")
    private LocalDateTime integratedAt;

    public SpotifyIntegrationLock() {
    }

    public SpotifyIntegrationLock(Boolean integrated, LocalDateTime localDate) {
        this.integrated = integrated;
        this.integratedAt = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Boolean integrated) {
        this.integrated = integrated;
    }

    public LocalDateTime getIntegratedAt() {
        return integratedAt;
    }

    public void setIntegratedAt(LocalDateTime integratedAt) {
        this.integratedAt = integratedAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final SpotifyIntegrationLock other = (SpotifyIntegrationLock) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SpotifyIntegrationLock{" + "id=" + id + ", integrated=" + integrated + ", localDate=" + integratedAt + '}';
    }
}
