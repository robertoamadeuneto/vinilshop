package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.EnumSellStatus;
import com.vinilshop.domain.model.Sell;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases related to the {@link SellRepository}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SellRepositoryTest {

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindAll() {
        Page<Sell> sells = sellRepository.findAll(any(Pageable.class));
        Assertions.assertThat(sells).isNotNull();
        Assertions.assertThat(sells.getContent().size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindOne() {
        Sell sell = sellRepository.findOne(1L);
        Assertions.assertThat(sell).isNotNull();
        Assertions.assertThat(sell.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldFindFinishedAtBetween() {
        LocalDate initialDate = LocalDate.of(2019, 2, 4);
        LocalDate finalDate = LocalDate.of(2019, 2, 4);
        Page<Sell> sells = sellRepository.findByFinishedAtBetween(initialDate, finalDate, any(Pageable.class));
        Assertions.assertThat(sells).isNotNull();
        Assertions.assertThat(sells.getContent().size()).isEqualTo(1);
    }

    @Test
    public void shouldSave() {
        Sell sell = new Sell();
        sell.setPrice(new BigDecimal("66.00"));
        sell.setCashback(new BigDecimal("13.20"));
        sell.setSellStatus(EnumSellStatus.COMPLETED);
        sell.setFinishedAt(LocalDate.of(2019, 2, 8));
        sell = sellRepository.save(sell);
        Assertions.assertThat(sell.getId()).isNotNull();
        sellRepository.delete(sell.getId());
        Assertions.assertThat(sellRepository.findOne(sell.getId())).isNull();
    }
}
