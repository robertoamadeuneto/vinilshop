package com.vinilshop.domain.service.impl;

import com.vinilshop.application.exception.SellCompletedException;
import com.vinilshop.domain.model.EnumSellStatus;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vinilshop.domain.service.SellService;
import com.vinilshop.infra.repository.SellItemRepository;
import com.vinilshop.infra.repository.SellRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Class responsible for all service cases related to the {@link Sell} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Service
public class SellServiceImpl implements SellService {
    
    private final SellRepository sellRepository;
    private final SellItemRepository sellItemRepository;
    
    @Autowired
    public SellServiceImpl(SellRepository sellRepository,
            SellItemRepository sellItemRepository) {
        this.sellRepository = sellRepository;
        this.sellItemRepository = sellItemRepository;
    }
    
    @Override
    @Transactional
    public Sell initiate() {
        return sellRepository.save(new Sell(new BigDecimal(0), new BigDecimal(0), EnumSellStatus.PENDING, null));
    }
    
    @Override
    public Page<Sell> findAll(Pageable pageable) {
        return this.sellRepository.findAll(pageable);
    }
    
    @Override
    public Sell findById(Long id) {
        return sellRepository.findOne(id);
    }
    
    @Override
    @Transactional
    public Sell finish(Sell sell) {
        verifySellStatus(sell);
        sell.setSellStatus(EnumSellStatus.COMPLETED);
        sell.setFinishedAt(LocalDate.now());
        return sellRepository.save(sell);
    }
    
    @Override
    public void verifySellStatus(Sell sell) {
        if (sell.getSellStatus().equals(EnumSellStatus.COMPLETED)) {
            throw new SellCompletedException(sell.getId());
        }
    }
    
    @Override
    public void recalculatePriceAndCashback(Sell sell) {
        Collection<SellItem> sellItems = this.sellItemRepository.findBySell(sell);
        BigDecimal price = new BigDecimal("0.00");
        BigDecimal cashback = new BigDecimal("0.00");
        for (SellItem sellItem : sellItems) {
            price = price.add(sellItem.getPrice());
            cashback = cashback.add(sellItem.getCashback());
        }
        sell.setPrice(price);
        sell.setCashback(cashback);
        sellRepository.save(sell);
    }
    
    @Override
    public Page<Sell> findByFinishedAtBetween(LocalDate initialDate, LocalDate finalDate, Pageable pageable) {
        return sellRepository.findByFinishedAtBetween(initialDate, finalDate, pageable);
    }
}
