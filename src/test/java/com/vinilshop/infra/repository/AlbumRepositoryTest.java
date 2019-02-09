package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.EnumGenre;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import static org.mockito.Matchers.any;
import org.springframework.data.domain.Page;

/**
 * Test cases related to the {@link AlbumRepository}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void shouldFindAll() {
        Page<Album> albums = albumRepository.findAll(any(Pageable.class));
        Assertions.assertThat(albums).isNotNull();
        Assertions.assertThat(albums.getContent().size()).isEqualTo(1);
    }

    @Test
    public void shouldFindOne() {
        Album album = albumRepository.findOne(1L);
        Assertions.assertThat(album).isNotNull();
        Assertions.assertThat(album.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldFindByGenreOrderByName() {
        Page<Album> albums = albumRepository.findByGenreOrderByNameAsc(EnumGenre.ROCK, any(Pageable.class));
        Assertions.assertThat(albums).isNotNull();
        Assertions.assertThat(albums.getContent().size()).isEqualTo(1);
    }
}
