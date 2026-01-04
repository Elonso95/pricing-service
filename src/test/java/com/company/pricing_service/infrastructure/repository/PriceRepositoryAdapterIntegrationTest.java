package com.company.pricing_service.infrastructure.repository;

import com.company.pricing_service.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PriceRepositoryAdapterIntegrationTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Test
    void findsApplicablePrices() {
        PriceRepositoryAdapter adapter = new PriceRepositoryAdapter(jpaPriceRepository);
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        List<Price> prices = adapter.findApplicablePrices(35455L, 1L, date);
        assertFalse(prices.isEmpty());
        assertTrue(prices.stream()
                .allMatch(p ->
                        p.getProductId().equals(35455L)
                                && p.getBrandId().equals(1L)
                                && !date.isBefore(p.getStartDate())
                                && !date.isAfter(p.getEndDate())));
    }
}
