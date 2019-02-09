package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import com.vinilshop.infra.repository.SellItemRepository;
import com.vinilshop.infra.repository.SellRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
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
 * Test cases related to the {@link SellItemRepository}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SellItemRepositoryTest {

    @Autowired
    private SellItemRepository sellItemRepository;

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindBySell() {
        Collection<SellItem> sellItems = sellItemRepository.findBySell(new Sell(1l));
        Assertions.assertThat(sellItems).isNotNull();
        Assertions.assertThat(sellItems.size()).isEqualTo(1);
    }

    @Test
    public void shouldFindBySellAndId() {
        SellItem sellItem = sellItemRepository.findBySellAndId(new Sell(1l), 1l);
        Assertions.assertThat(sellItem).isNotNull();
        Assertions.assertThat(sellItem.getId()).isEqualTo(1);
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
        SellItem sellItem = new SellItem();
        sellItem.setSell(new Sell(1l));
        sellItem.setAlbum(new Album(1l));
        sellItem.setPrice(new BigDecimal("66.00"));
        sellItem.setCashback(new BigDecimal("13.20"));
        sellItem = sellItemRepository.save(sellItem);
        Assertions.assertThat(sellItem.getId()).isNotNull();
        sellItemRepository.delete(sellItem.getId());
    }

    @Test
    public void shouldRemove() {
        SellItem sellItem = new SellItem();
        sellItem.setSell(new Sell(1l));
        sellItem.setAlbum(new Album(1l));
        sellItem.setPrice(new BigDecimal("66.00"));
        sellItem.setCashback(new BigDecimal("13.20"));
        sellItem = sellItemRepository.save(sellItem);
        Assertions.assertThat(sellItem.getId()).isNotNull();
        sellItemRepository.delete(sellItem.getId());
    }
}
