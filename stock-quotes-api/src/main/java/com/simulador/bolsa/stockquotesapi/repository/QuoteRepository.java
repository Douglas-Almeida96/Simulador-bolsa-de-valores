package com.simulador.bolsa.stockquotesapi.repository;

import com.simulador.bolsa.stockquotesapi.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @RestResource(rel = "quotes", path = "quotes")
    Page<Quote> findAllBySymbol(@Param(("symbol")) String symbol, Pageable page);
}
