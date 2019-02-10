package com.vinilshop.application.controller;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import com.vinilshop.infra.repository.SellItemRepository;
import com.vinilshop.infra.repository.SellRepository;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

/**
 * Test cases related to the {@link SellItemController}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SellItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SellItemRepository sellItemRepository;

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindBySell() {
        SellItem[] sellItems = restTemplate.getForObject("/api/sells/1/items", SellItem[].class);
        Assertions.assertThat(sellItems.length).isGreaterThan(0);
    }

    @Test
    public void shouldFindSellAndId() {
        ResponseEntity<SellItem> response = restTemplate.getForEntity("/api/sells/1/items/1", SellItem.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldAdd() {
        ResponseEntity<Sell> responseSell = restTemplate.postForEntity("/api/sells", null, Sell.class);
        Assertions.assertThat(responseSell.getStatusCodeValue()).isEqualTo(201);
        Sell sell = responseSell.getBody();

        SellItem sellItem = new SellItem();
        sellItem.setSell(sell);
        sellItem.setAlbum(new Album(1L));
        ResponseEntity<SellItem> responseSellItem = restTemplate.postForEntity("/api/sells/" + sell.getId() + "/items", sellItem, SellItem.class);
        Assertions.assertThat(responseSellItem.getStatusCodeValue()).isEqualTo(201);
        sellItem = responseSellItem.getBody();

        sellItemRepository.delete(sellItem.getId());
        sellRepository.delete(sell.getId());
    }

    @Test
    public void shouldRemove() throws Exception {
        ResponseEntity<Sell> responseSell = restTemplate.postForEntity("/api/sells", null, Sell.class);
        Assertions.assertThat(responseSell.getStatusCodeValue()).isEqualTo(201);
        Sell sell = responseSell.getBody();

        SellItem sellItem = new SellItem();
        sellItem.setSell(sell);
        sellItem.setAlbum(new Album(1l));

        ResponseEntity<SellItem> responseSellItem = restTemplate.postForEntity("/api/sells/" + sell.getId() + "/items", sellItem, SellItem.class);
        Assertions.assertThat(responseSellItem.getStatusCodeValue()).isEqualTo(201);
        sellItem = responseSellItem.getBody();

        restTemplate.delete("/api/sells/" + sell.getId() + "/items/" + sellItem.getId());
        responseSellItem = restTemplate.getForEntity("/api/sells/" + sell.getId() + "/items/" + sellItem.getId(), SellItem.class);
        Assertions.assertThat(responseSellItem.getBody()).isNull();

        sellRepository.delete(sell.getId());
    }
}
