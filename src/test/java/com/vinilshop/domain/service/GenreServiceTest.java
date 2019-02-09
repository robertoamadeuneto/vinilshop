package com.vinilshop.domain.service;

import com.vinilshop.domain.model.Genre;
import com.vinilshop.domain.service.GenreService;
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
 * Test cases related to the {@link GenreService}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    public void shouldFindAll() {
        Page<Genre> genres = genreService.findAll(any(Pageable.class));
        Assertions.assertThat(genres).isNotNull();
        Assertions.assertThat(genres.getContent().size()).isGreaterThan(0);
    }

    @Test
    public void shouldFindById() {
        Genre genre = genreService.findById(1L);
        Assertions.assertThat(genre).isNotNull();
        Assertions.assertThat(genre.getId()).isEqualTo(1L);
    }
}
