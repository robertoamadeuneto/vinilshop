package com.vinilshop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to add a sell item and the sell id on request
 * body conflicts with the URL one.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SellIdConflictException extends RuntimeException {

    public SellIdConflictException(Long id) {
        super("The Sell on request body is not the same on the URL. ID: " + id + ".");
    }
}
