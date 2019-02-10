//package com.vinilshop.application.filter;
//
//import com.vinilshop.domain.integration.spotify.SpotifyIntegration;
//import com.vinilshop.domain.integration.spotify.impl.SpotifyIntegrationImpl;
//import com.vinilshop.infra.repository.SpotifyIntegrationLockRepository;
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
///**
// * A filter that verifies in each request if the Spotify API integration was
// * done and starts it if not.
// *
// * @author Roberto Amadeu Neto
// * @since 10/02/2019
// * @version 1.0
// */
//@Component
//@Order(1)
//public class SpotifyIntegrationFilter implements Filter {
//
//    private final SpotifyIntegration spotifyIntegration;
//    private final SpotifyIntegrationLockRepository spotifyIntegrationLockRepository;
//    private final Logger logger = LoggerFactory.getLogger(SpotifyIntegrationFilter.class);
//
//    @Autowired
//    public SpotifyIntegrationFilter(SpotifyIntegrationImpl spotifyIntegration,
//            SpotifyIntegrationLockRepository spotifyIntegrationLockRepository) {
//        this.spotifyIntegration = spotifyIntegration;
//        this.spotifyIntegrationLockRepository = spotifyIntegrationLockRepository;
//    }
//
//    /**
//     * Does the request filter and starts the Spotify API integration if
//     * necessary.
//     *
//     * @param sr a {@link ServletRequest} object.
//     * @param sr1 a {@link ServletResponse} object.
//     * @param fc a {@link FilterChain} object.
//     * @throws IOException a I/O exception.
//     * @throws ServletException a servlet exception.
//     */
//    @Override
//    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
//        try {
//            if (CollectionUtils.isEmpty(spotifyIntegrationLockRepository.findAll())) {
//                spotifyIntegration.integrate();
//            }
//        } catch (Exception ex) {
//            logger.error(ex.getMessage());
//        }
//    }
//
//    @Override
//    public void init(FilterConfig fc) throws ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
