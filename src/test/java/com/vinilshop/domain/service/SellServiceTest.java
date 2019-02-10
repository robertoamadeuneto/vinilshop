package com.vinilshop.domain.service;

import com.vinilshop.application.exception.SellCompletedException;
import com.vinilshop.domain.model.EnumSellStatus;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.infra.repository.SellRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases related to the {@link SellService}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellServiceTest {

    @Autowired
    private SellService sellService;

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindAll() {
        Page<Sell> sells = sellService.findAll(any(Pageable.class));
        Assertions.assertThat(sells).isNotNull();
        Assertions.assertThat(sells.getContent().size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindById() {
        Sell sell = sellService.findById(1L);
        Assertions.assertThat(sell).isNotNull();
        Assertions.assertThat(sell.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldFindFinishedAtBetween() {
        LocalDate initialDate = LocalDate.of(2019, 2, 4);
        LocalDate finalDate = LocalDate.of(2019, 2, 4);
        Page<Sell> sells = sellService.findByFinishedAtBetween(initialDate, finalDate, any(Pageable.class));
        Assertions.assertThat(sells).isNotNull();
        Assertions.assertThat(sells.getContent().size()).isEqualTo(1);
    }

    @Test
    public void shouldInitiate() {
        Sell sell = sellService.initiate();
        Assertions.assertThat(sell.getId()).isNotNull();
        sellRepository.delete(sell.getId());
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }

    @Test
    public void shouldFinish() {
        Sell sell = sellService.initiate();
        Assertions.assertThat(sell.getId()).isNotNull();
        sellService.finish(sell);
        Assertions.assertThat(sell.getSellStatus()).isEqualTo(EnumSellStatus.COMPLETED);
        sellRepository.delete(sell.getId());
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }

    @Test
    public void shouldVerifyIfStatusIsNotCompleted() {
        Sell sell = sellService.initiate();
        sellService.verifySellStatus(sell);
        sellRepository.delete(sell.getId());
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }

    @Test(expected = SellCompletedException.class)
    public void shouldVerifyIfStatusIsCompleted() {
        Sell sell = sellService.initiate();
        sellService.finish(sell);
        sellService.verifySellStatus(sell);
        sellRepository.delete(sell.getId());
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }
    
    public void shouldRecalculatePriceAndCashback() {
        Sell sell = sellService.findById(1L);
        Assertions.assertThat(sell).isNotNull();
        Assertions.assertThat(sell.getId()).isEqualTo(1L);
        sellService.recalculatePriceAndCashback(sell);
        sell = sellService.findById(1L);
        Assertions.assertThat(sell.getPrice()).isEqualTo(new BigDecimal("66.00"));
        Assertions.assertThat(sell.getCashback()).isEqualTo(new BigDecimal("6.0"));
    }
}
