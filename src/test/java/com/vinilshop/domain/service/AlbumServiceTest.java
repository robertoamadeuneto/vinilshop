package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.service.AlbumService;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import static org.mockito.Matchers.any;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

/**
 * Test cases related to the {@link AlbumService}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @Test
    public void shouldFindAll() {
        Page<Album> albums = albumService.findAll(any(Pageable.class));
        Assertions.assertThat(albums).isNotNull();
        Assertions.assertThat(albums.getContent().size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindById() {
        Album album = albumService.findById(1L);
        Assertions.assertThat(album).isNotNull();
        Assertions.assertThat(album.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldFindByGenreOrderByName() {
        Page<Album> albums = albumService.findByGenreOrderByNameAsc("rock", any(Pageable.class));
        Assertions.assertThat(albums).isNotNull();
        Assertions.assertThat(albums.getContent().size()).isGreaterThan(0);
    }
}
