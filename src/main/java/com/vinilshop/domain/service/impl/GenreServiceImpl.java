package com.vinilshop.domain.service.impl;

import com.vinilshop.domain.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinilshop.domain.service.GenreService;
import com.vinilshop.infra.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Class responsible for all service cases related to the {@link Genre} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 06/02/2019
 * @version 1.0
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Page<Genre> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findOne(id);
    }
}
