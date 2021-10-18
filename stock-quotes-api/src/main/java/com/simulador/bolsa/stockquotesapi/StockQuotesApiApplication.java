package com.simulador.bolsa.stockquotesapi;

import com.simulador.bolsa.stockquotesapi.service.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Log4j2
@EnableScheduling
@SpringBootApplication
public class StockQuotesApiApplication {
	private QuoteService quoteService;

	@Autowired
	public StockQuotesApiApplication(QuoteService quoteService) {
		this.quoteService = quoteService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockQuotesApiApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000)
	public void generateData(){
		log.info(quoteService.findFirstBySymbolOrderByTimestampDesc("test"));
	}


}
