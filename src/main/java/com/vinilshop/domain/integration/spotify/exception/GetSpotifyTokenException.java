package com.vinilshop.domain.integration.spotify.exception;

/**
 * Exception thrown when trying get a token on Spotify API.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
public class GetSpotifyTokenException extends RuntimeException {

    public GetSpotifyTokenException(String message) {
        super("Error trying to get a token on Spotify API. Cause: " + message);
    }
}
