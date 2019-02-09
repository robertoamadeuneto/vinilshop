package com.vinilshop.domain.service.impl;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.EnumGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinilshop.domain.service.AlbumService;
import com.vinilshop.infra.repository.AlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Class responsible for all service cases related to the {@link Album} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findOne(id);
    }

    @Override
    public Page<Album> findByGenreOrderByNameAsc(String genreDescription, Pageable pageable) {
        return albumRepository.findByGenreOrderByNameAsc(EnumGenre.getEnumGenreByDescription(genreDescription), pageable);
    }
}
