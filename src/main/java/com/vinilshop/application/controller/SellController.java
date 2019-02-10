package com.vinilshop.application.controller;

import com.vinilshop.application.exception.ResourceNotFoundException;
import com.vinilshop.domain.model.Sell;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vinilshop.domain.service.SellService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link Sell} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/sells")
public class SellController {

    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    /**
     * Finds all {@link Sell}.
     *
     * @param initialDate a {@link LocalDateTime} to filter the initial date.
     * @param finalDate a {@link LocalDateTime} to filter the final date.
     * @param pageable a {@link Pageable} object.
     * @return a {@link ResponseEntity} with the list of all {@link Sell}.
     */
    @GetMapping
    @ApiOperation(value = "Returns a list with all sells.",
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<Page<Sell>> findAll(@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate initialDate,
            @RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finalDate,
            Pageable pageable) {
        return new ResponseEntity<>(initialDate != null && finalDate != null
                ? sellService.findByFinishedAtBetween(initialDate, finalDate, pageable)
                : sellService.findAll(pageable),
                HttpStatus.OK);
    }

    /**
     * Finds an {@link Sell} by its identifier.
     *
     * @param id a {@link Sell} identifier.
     * @return a {@link ResponseEntity} with the {@link Sell} object.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns a sell by a given identifier.",
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<Sell> findById(@PathVariable("id") Long id) {
        Sell sell = sellService.findById(id);
        if (sell == null) {
            throw new ResourceNotFoundException(id);
        }
        return new ResponseEntity<>(sell, HttpStatus.OK);
    }

    /**
     * Initiates a new {@link Sell}.
     *
     * @return the new {@link Sell} on response.
     */
    @PostMapping
    @ApiOperation(value = "Initiates a new sell.",
            produces = "application/json",
            httpMethod = "POST",
            code = 201)
    public ResponseEntity<Sell> initiate() {
        return new ResponseEntity<>(sellService.initiate(), HttpStatus.CREATED);
    }

    /**
     * Finishes a {@link Sell}.
     *
     * @param id a {@link Sell} identifier.
     * @return the new {@link Sell} on response.
     */
    @PatchMapping(path = "/{id}")
    @ApiOperation(value = "Finishes a sell.",
            produces = "application/json",
            httpMethod = "PATCH",
            code = 200)
    public ResponseEntity<Sell> finish(@PathVariable("id") Long id) {
        Sell sell = sellService.findById(id);
        if (sell == null) {
            throw new ResourceNotFoundException(id);
        }
        return new ResponseEntity<>(sellService.finish(sell), HttpStatus.OK);
    }
}
