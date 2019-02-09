package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import com.vinilshop.domain.service.SellItemService;
import com.vinilshop.domain.service.SellService;
import com.vinilshop.infra.repository.SellRepository;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases related to the {@link SellItemService}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellItemServiceTest {

    @Autowired
    private SellItemService sellItemService;

    @Autowired
    private SellService sellService;

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindBySell() {
        Collection<SellItem> sellItems = sellItemService.findBySell(new Sell(1l));
        Assertions.assertThat(sellItems).isNotNull();
        Assertions.assertThat(sellItems.size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindBySellAndId() {
        SellItem sellItem = sellItemService.findBySellAndId(new Sell(1l), 1l);
        Assertions.assertThat(sellItem).isNotNull();
        Assertions.assertThat(sellItem.getId()).isEqualTo(1);
    }

    @Test
    public void shouldAdd() {
        Sell sell = sellService.initiate();
        Assertions.assertThat(sell.getId()).isNotNull();

        SellItem sellItem = new SellItem();
        sellItem.setSell(sell);
        sellItem.setAlbum(new Album(1l));

        sellItem = sellItemService.add(sell, sellItem);
        Assertions.assertThat(sellItem.getId()).isNotNull();

        sellItemService.remove(sell, sellItem.getId());
        Assertions.assertThat(sellItemService.findBySellAndId(sell, sellItem.getId())).isNull();

        sellRepository.delete(sell);
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }

    @Test
    public void shouldRemove() {
        Sell sell = sellService.initiate();
        Assertions.assertThat(sell.getId()).isNotNull();

        SellItem sellItem = new SellItem();
        sellItem.setSell(sell);
        sellItem.setAlbum(new Album(1l));

        sellItem = sellItemService.add(sell, sellItem);
        Assertions.assertThat(sellItem.getId()).isNotNull();

        sellItemService.remove(sell, sellItem.getId());
        Assertions.assertThat(sellItemService.findBySellAndId(sell, sellItem.getId())).isNull();

        sellRepository.delete(sell);
        Assertions.assertThat(sellService.findById(sell.getId())).isNull();
    }
}
