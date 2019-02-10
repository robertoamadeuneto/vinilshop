package com.vinilshop.application.controller;

import com.vinilshop.application.exception.ResourceNotFoundException;
import com.vinilshop.domain.model.Album;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vinilshop.domain.service.AlbumService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link Album} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/albums")
public class AlbumController implements Serializable {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Finds all {@link Album}.
     *
     * @param genreDescription a {@link String} with the genre description.
     * @param pageable a {@link Pageable} object.
     * @return a {@link ResponseEntity} with the list of all {@link Album}.
     */
    @GetMapping
    @ApiOperation(value = "Returns a list with all albums.",
            produces = "application/json",
            httpMethod = "GET",
            response = Album[].class,
            code = 200)
    public ResponseEntity<?> findAll(@RequestParam(value = "genre", required = false) String genreDescription,
            Pageable pageable) {
        return new ResponseEntity<>(genreDescription != null
                ? albumService.findByGenreOrderByNameAsc(genreDescription, pageable)
                : albumService.findAll(pageable), HttpStatus.OK);
    }

    /**
     * Finds an {@link Album} by its identifier.
     *
     * @param id the {@link Album} identifier.
     * @return a {@link ResponseEntity} with the {@link Album} object.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns an album by a given identifier.",
            produces = "application/json",
            httpMethod = "GET",
            response = Album.class,
            code = 200)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Album album = albumService.findById(id);
        if (album == null) {
            throw new ResourceNotFoundException(id);
        }
        return new ResponseEntity<>(album, HttpStatus.OK);
    }
}
