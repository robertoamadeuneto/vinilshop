package com.vinilshop.infra.listener;

import com.vinilshop.domain.integration.spotify.SpotifyIntegration;
import com.vinilshop.domain.integration.spotify.impl.SpotifyIntegrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * A component that listen the spring boot events and starts an integration on
 * the application startup.
 *
 * @author Roberto Amadeu Neto
 * @since 08/02/2019
 * @version 1.0
 */
@Profile("production")
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final SpotifyIntegration spotifyIntegration;

    @Autowired
    public ApplicationStartupListener(SpotifyIntegrationImpl spotifyIntegration) {
        this.spotifyIntegration = spotifyIntegration;
    }

    /**
     * Application event listener.
     *
     * @param applicationReadyEvent a {@link ApplicationReadyEvent} object.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        spotifyIntegration.integrate();
    }
}
