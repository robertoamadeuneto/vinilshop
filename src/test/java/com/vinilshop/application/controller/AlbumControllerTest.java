package com.vinilshop.application.controller;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.service.AlbumService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test cases related to the {@link AlbumController}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AlbumControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldFindAll() {
        ResponseEntity<Album> response = restTemplate.getForEntity("/api/albums", Album.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void shouldFindAllFilteringGenre() {
        ResponseEntity<Album> response = restTemplate.getForEntity("/api/albums?genre=rock", Album.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void shouldFindById() {
        ResponseEntity<Album> response = restTemplate.getForEntity("/api/albums/1", Album.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
