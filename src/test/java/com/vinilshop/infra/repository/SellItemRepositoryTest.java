package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import java.math.BigDecimal;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

    @Test
    public void shouldFindBySell() {
        Collection<SellItem> sellItems = sellItemRepository.findBySell(new Sell(1l));
        Assertions.assertThat(sellItems).isNotNull();
        Assertions.assertThat(sellItems.size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindBySellAndId() {
        SellItem sellItem = sellItemRepository.findBySellAndId(new Sell(1l), 1l);
        Assertions.assertThat(sellItem).isNotNull();
        Assertions.assertThat(sellItem.getId()).isEqualTo(1);
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
        Assertions.assertThat(sellItemRepository.findOne(sellItem.getId())).isNull();
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
        Assertions.assertThat(sellItemRepository.findOne(sellItem.getId())).isNull();
    }
}
