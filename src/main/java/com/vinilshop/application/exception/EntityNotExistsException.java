package com.vinilshop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to find an entity that not exists.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotExistsException extends RuntimeException {

    public EntityNotExistsException(Long entityId) {
        super("Entity not exists exception. ID: " + entityId);
    }
}
