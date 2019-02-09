package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.SpotifyIntegrationLock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link SpotifyIntegrationLockRepository} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
public interface SpotifyIntegrationLockRepository extends JpaRepository<SpotifyIntegrationLock, Long> {

}
