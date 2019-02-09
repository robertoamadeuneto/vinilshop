package com.vinilshop.domain.service.impl;

import com.vinilshop.domain.model.Album;
import com.vinilshop.domain.model.Genre;
import com.vinilshop.domain.model.Sell;
import com.vinilshop.domain.model.SellItem;
import com.vinilshop.domain.service.AlbumService;
import com.vinilshop.domain.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinilshop.domain.service.SellItemService;
import com.vinilshop.domain.service.SellService;
import com.vinilshop.infra.repository.SellItemRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for all service cases related to the {@link SellItem}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
@Service
public class SellItemServiceImpl implements SellItemService {

    private final SellService sellService;
    private final AlbumService albumService;
    private final GenreService genreService;
    private final SellItemRepository sellItemRepository;

    @Autowired
    public SellItemServiceImpl(SellService sellService,
            AlbumService albumService,
            GenreService genreService,
            SellItemRepository sellItemRepository) {
        this.sellService = sellService;
        this.albumService = albumService;
        this.genreService = genreService;
        this.sellItemRepository = sellItemRepository;
    }

    @Override
    public Collection<SellItem> findBySell(Sell sell) {
        return sellItemRepository.findBySell(sell);
    }

    @Override
    public SellItem findBySellAndId(Sell sell, Long id) {
        return sellItemRepository.findBySellAndId(sell, id);
    }

    @Override
    @Transactional
    public SellItem add(Sell sell, SellItem sellItem) {
        sellService.verifySellStatus(sell);

        Album album = albumService.findById(sellItem.getAlbum().getId());
        sellItem.setPrice(album.getPrice());

        Genre genre = genreService.findById((long) album.getGenre().getId());
        sellItem.setCashback(calculateCashback(album, genre));

        sellItem = sellItemRepository.save(sellItem);

        sellService.recalculatePriceAndCashback(sell);
        
        return sellItem;
    }

    @Override
    @Transactional
    public void remove(Sell sell, Long id) {
        sellService.verifySellStatus(sell);
        sellItemRepository.delete(id);
    }

    /**
     * Calculates the cashback of a sell item.
     *
     * @param album a {@link Album} object.
     * @param genre a {@link Genre} object.
     * @return a {@link BigDecimal} with the cashback.
     */
    private BigDecimal calculateCashback(Album album, Genre genre) {

        Long cashbackPercentage = 0L;
        switch (LocalDate.now().getDayOfWeek()) {
            case SUNDAY:
                cashbackPercentage = genre.getCashbackSunday();
                break;
            case MONDAY:
                cashbackPercentage = genre.getCashbackMonday();
                break;
            case THURSDAY:
                cashbackPercentage = genre.getCashbackThursday();
                break;
            case WEDNESDAY:
                cashbackPercentage = genre.getCashbackWednesday();
                break;
            case TUESDAY:
                cashbackPercentage = genre.getCashbackTuesday();
                break;
            case FRIDAY:
                cashbackPercentage = genre.getCashbackFriday();
                break;
            case SATURDAY:
                cashbackPercentage = genre.getCashbackSaturday();
                break;
        }
        
       return (album.getPrice().multiply(new BigDecimal(cashbackPercentage))).divide(new BigDecimal("100.00"));
    }
}
