package com.vinilshop.domain.integration.spotify.impl;

import com.vinilshop.domain.integration.spotify.SpotifyIntegration;
import com.vinilshop.domain.integration.spotify.exception.GetSpotifyAlbumsException;
import com.vinilshop.domain.integration.spotify.exception.GetSpotifyTokenException;
import com.vinilshop.domain.model.EnumGenre;
import com.vinilshop.domain.integration.spotify.mapper.SearchMapper;
import com.vinilshop.domain.integration.spotify.mapper.TokenMapper;
import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.SpotifyIntegrationLock;
import com.vinilshop.infra.repository.AlbumRepository;
import com.vinilshop.infra.repository.SpotifyIntegrationLockRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Class responsible for all service cases related to the Spotify integration.
 *
 * @author Roberto Amadeu Neto
 * @since 07/02/2019
 * @version 1.0
 */
@Service
public class SpotifyIntegrationImpl implements SpotifyIntegration {

    private final SpotifyIntegrationLockRepository spotifyIntegrationLockRepository;
    private final AlbumRepository albumRepository;
    private static final String SPOTIFY_BASE64CODE = "ZWIyYjM4MDYwYjhiNDE0NThkNGVjZmMxMWRjMTJjOTU6YzZmNDAyMWFjYzMxNDJlZThkMDE5YTEzNDJlYzU1Y2U=";
    private static final String URL_SPOTIFY_TOKEN = "https://accounts.spotify.com/api/token";
    private static final String URL_SPOTIFY_SEARCH = "https://api.spotify.com/v1/search?type=album&limit=50";

    @Autowired
    public SpotifyIntegrationImpl(SpotifyIntegrationLockRepository spotifyIntegrationLockRepository,
            AlbumRepository albumRepository) {
        this.spotifyIntegrationLockRepository = spotifyIntegrationLockRepository;
        this.albumRepository = albumRepository;
    }

    /**
     * Does the integration of Spotify API albums to this application.
     */
    @Override
    @Transactional
    public void integrate() {
        if (CollectionUtils.isEmpty(spotifyIntegrationLockRepository.findAll())) {
            integrateAlbums(getToken());
            lockIntegration();
        }
    }

    /**
     * Gets a token on Spotify API to perform requests.
     *
     * @return a {@link TokenMapper} object.
     */
    private TokenMapper getToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Authorization", "Basic " + SPOTIFY_BASE64CODE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<TokenMapper> response = restTemplate.exchange(URL_SPOTIFY_TOKEN,
                    HttpMethod.POST,
                    request,
                    TokenMapper.class);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            throw new GetSpotifyTokenException(ex.getMessage());
        }
    }

    /**
     * Gets 50 albums of each genre from Spotify API and saves on database.
     *
     * @param tokenMapper a {@link TokenMapper} object.
     */
    @Transactional
    private void integrateAlbums(TokenMapper tokenMapper) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + tokenMapper.getAccessToken());

        HttpEntity<String> httpEntity = new HttpEntity<>("headers", httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        for (EnumGenre genre : EnumGenre.values()) {
            try {
                ResponseEntity<SearchMapper> response = restTemplate.exchange(URL_SPOTIFY_SEARCH + "&q=" + genre.getDescription(),
                        HttpMethod.GET,
                        httpEntity,
                        SearchMapper.class);

                if (response.getStatusCode().equals(HttpStatus.OK)
                        && !Objects.isNull(response.getBody())) {
                    saveAlbumsByGenre(response.getBody(), genre);
                }
            } catch (HttpClientErrorException ex) {
                throw new GetSpotifyAlbumsException(ex.getMessage());
            }
        }
    }

    /**
     * Saves the albums by genre from the Spotify API on database.
     *
     * @param genre a {@link EnumGenre} option.
     * @param searchMapper a {@link SearchMapper} object.
     */
    @Transactional
    private void saveAlbumsByGenre(SearchMapper searchMapper, EnumGenre genre) {
        searchMapper.getAlbums().getAlbums().stream().map((am) -> {
            Album album = new Album();
            album.setName(am.getName());
            album.setArtist(am.getArtists().size() > 1 ? am.getArtists().get(0).getName() : null);
            album.setGenre(genre);
            album.setPrice(new BigDecimal(new Random().nextInt(99) + 1));
            return album;
        }).forEachOrdered((album) -> {
            albumRepository.save(album);
        });
    }

    /**
     * Locks the integration for the next startups.
     */
    @Transactional
    private void lockIntegration() {
        spotifyIntegrationLockRepository.save(new SpotifyIntegrationLock(true, LocalDateTime.now()));
    }
}
