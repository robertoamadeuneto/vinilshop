package com.vinilshop.infra.repository;

import com.vinilshop.domain.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Sell} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2019
 * @version 1.0
 */
public interface SellRepository extends JpaRepository<Sell, Long> {

}
