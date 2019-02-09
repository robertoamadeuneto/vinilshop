package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Genre;
import com.vinilshop.infra.repository.GenreRepository;
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
 * Test cases related to the {@link GenreRepository}.
 *
 * @author Roberto Amadeu Neto
 * @since 09/02/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void shouldFindAll() {
        Page<Genre> genres = genreRepository.findAll(any(Pageable.class));
        Assertions.assertThat(genres).isNotNull();
        Assertions.assertThat(genres.getContent().size()).isEqualTo(4);
    }

    @Test
    public void shouldFindOne() {
        Genre genre = genreRepository.findOne(1L);
        Assertions.assertThat(genre).isNotNull();
        Assertions.assertThat(genre.getId()).isEqualTo(1L);
    }
}
