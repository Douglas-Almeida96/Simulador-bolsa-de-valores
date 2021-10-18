package com.simulador.bolsa.stockquotesapi.service;

import com.simulador.bolsa.stockquotesapi.model.Quote;
import com.simulador.bolsa.stockquotesapi.repository.QuoteRepository;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote findFirstBySymbolOrderByTimestampDesc(String test) {
        Optional<Quote> quote = Optional.ofNullable(quoteRepository.findFirstBySymbolOrderByTimestampDesc(test)
                .map(this::generateNewData)
                .orElseGet(this::inicializeData));

        return quote.get();
    }

    private Quote inicializeData() {
        return quoteRepository.save(Quote.builder()
                .symbol("test")
                .openValue(0.222222)
                .closeValue(0.222222)
                .timestamp(new Date())
                .build());
    }

    private Quote generateNewData(Quote quote) {
        return quoteRepository.save(Quote.builder()
                .symbol(quote.getSymbol())
                .openValue(quote.getOpenValue()* new RandomDataGenerator().nextUniform(-0.10,0.10))
                .closeValue(quote.getCloseValue()* new RandomDataGenerator().nextUniform(-0.10,0.10))
                .timestamp(new Date())
                .build());
    }
}
