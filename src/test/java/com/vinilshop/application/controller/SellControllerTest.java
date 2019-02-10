package com.vinilshop.application.controller;

import com.vinilshop.domain.model.Sell;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test cases related to the {@link SellController}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SellControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SellRepository sellRepository;

    @Test
    public void shouldFindAll() {
        ResponseEntity<Sell> response = restTemplate.getForEntity("/api/sells", Sell.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldFindAllFilteringFinishedAtRange() {
        ResponseEntity<Sell> response
                = restTemplate.getForEntity("/api/sells?initialDate=09/02/2019&finalDate=09/02/2019", Sell.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldFindById() {
        ResponseEntity<Sell> response = restTemplate.getForEntity("/api/sells/1", Sell.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldInitiate() {
        ResponseEntity<Sell> response = restTemplate.postForEntity("/api/sells", null, Sell.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        sellRepository.delete(response.getBody().getId());
    }

    @Test
    public void shouldFinish() throws Exception {
        ResponseEntity<Sell> response = restTemplate.postForEntity("/api/sells", null, Sell.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/sells/{id}", response.getBody().getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        sellRepository.delete(response.getBody().getId());
    }
}
