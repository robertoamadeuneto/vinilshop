package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.SpotifyIntegrationLock;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases related to the {@link SpotifyIntegrationLockRepository}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpotifyIntegrationLockRepositoryTest {

    @Autowired
    private SpotifyIntegrationLockRepository spotifyIntegrationLockRepository;

    @Test
    public void shouldSave() {
        SpotifyIntegrationLock spotifyIntegrationLock
                = new SpotifyIntegrationLock(true, LocalDateTime.now());
        spotifyIntegrationLock = spotifyIntegrationLockRepository.save(spotifyIntegrationLock);
        Assertions.assertThat(
                spotifyIntegrationLockRepository.findOne(spotifyIntegrationLock.getId())).isNotNull();
    }
}
