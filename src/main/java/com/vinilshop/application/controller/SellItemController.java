package com.vinilshop.application.controller;

import com.vinilshop.application.exception.ResourceNotFoundException;
import com.vinilshop.application.exception.SellIdConflictException;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import com.vinilshop.domain.service.SellItemService;
import com.vinilshop.domain.service.SellService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link SellItem} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/sells/{idSell}/items")
public class SellItemController {

    private final SellService sellService;
    private final SellItemService sellItemService;

    @Autowired
    public SellItemController(SellService sellService, SellItemService sellItemService) {
        this.sellService = sellService;
        this.sellItemService = sellItemService;
    }

    /**
     * Finds all {@link SellItem} of a {@link Sell}.
     *
     * @param idSell a {@link Sell} identifier.
     * @return a {@link ResponseEntity} with the list of all {@link SellItem} of
     * a {@link Sell}.
     */
    @GetMapping
    @ApiOperation(value = "Returns a list with all sell items.",
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findBySell(@PathVariable("idSell") Long idSell) {
        Sell sell = verifySell(idSell);
        return new ResponseEntity<>(sellItemService.findBySell(sell), HttpStatus.OK);
    }

    /**
     * Finds an {@link SellItem} by its {@link Sell} and identifier.
     *
     * @param idSell a {@link Sell} identifier.
     * @param id {@link SellItem} identifier.
     * @return a {@link ResponseEntity} with the {@link SellItem} of a
     * {@link Sell}.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns a sell by a given identifier.",
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findBySellAndId(@PathVariable("idSell") Long idSell,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(sellItemService.findBySellAndId(new Sell(idSell), id), HttpStatus.OK);
    }

    /**
     * Creates a new {@link Sell}.
     *
     * @param idSell the {@link Sell} identifier.
     * @param sellItem a {@link SellItem} object.
     * @return a {@link ResponseEntity} with the {@link SellItem} of a
     * {@link Sell}.
     */
    @PostMapping
    @ApiOperation(value = "Adds a new item to a sell.",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "POST",
            code = 201)
    public ResponseEntity<?> add(@PathVariable("idSell") Long idSell,
            @RequestBody SellItem sellItem) {
        Sell sell = verifySell(idSell);
        if (!sell.getId().equals(sellItem.getSell().getId())) {
            throw new SellIdConflictException(sellItem.getSell().getId());
        }
        return new ResponseEntity<>(sellItemService.add(sell, sellItem), HttpStatus.CREATED);
    }

    /**
     * Deletes {@link SellItem} from a {@link Sell}.
     *
     * @param idSell the {@link Sell} identifier.
     * @param id the {@link SellItem} identifier.
     * @return a {@link ResponseEntity}.
     */
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a cart.",
            httpMethod = "DELETE",
            code = 200)
    public ResponseEntity<?> remove(@PathVariable("idSell") Long idSell,
            @PathVariable("id") Long id) {
        SellItem sellItem = sellItemService.findBySellAndId(new Sell(idSell), id);
        if (sellItem == null) {
            throw new ResourceNotFoundException(idSell);
        }
        sellItemService.remove(sellItem.getSell(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Verifies if the {@link Sell} exists and returns it.
     *
     * @param idSell the {@link Sell} identifier.
     * @return a {@link Sell} object.
     */
    private Sell verifySell(Long idSell) {
        Sell sell = sellService.findById(idSell);
        if (sell == null) {
            throw new ResourceNotFoundException(idSell);
        }
        return sell;
    }
}
