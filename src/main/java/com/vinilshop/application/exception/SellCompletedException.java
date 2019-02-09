package com.vinilshop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to perform an operation on a completed sell.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SellCompletedException extends RuntimeException {

    public SellCompletedException(Long id) {
        super("This Sell is already completed. ID: " + id);
    }
}
