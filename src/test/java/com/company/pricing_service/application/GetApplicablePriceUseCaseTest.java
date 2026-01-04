package com.company.pricing_service.application;

import com.company.pricing_service.application.usecase.GetApplicablePriceUseCase;
import com.company.pricing_service.application.usecase.PriceNotFoundException;
import com.company.pricing_service.domain.model.Price;
import com.company.pricing_service.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetApplicablePriceUseCaseTest {

    @Test
    void returnsPriceWithHighestPriority() {
        PriceRepository repository = mock(PriceRepository.class);
        GetApplicablePriceUseCase useCase = new GetApplicablePriceUseCase(repository);
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);

        Price lowPriority = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(date.minusHours(1))
                .endDate(date.plusHours(1))
                .priceList(1)
                .priority(0)
                .price(35.50)
                .currency("EUR")
                .build();

        Price highPriority = Price.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(date.minusHours(1))
                .endDate(date.plusHours(1))
                .priceList(2)
                .priority(1)
                .price(25.45)
                .currency("EUR")
                .build();

        when(repository.findApplicablePrices(35455L, 1L, date))
                .thenReturn(Arrays.asList(lowPriority, highPriority));
        Price result = useCase.execute(35455L, 1L, date);
        assertEquals(2, result.getPriceList());
        assertEquals(1, result.getPriority());
    }

    @Test
    void throwsExceptionWhenNoPriceFound() {
        PriceRepository repository = mock(PriceRepository.class);
        GetApplicablePriceUseCase useCase = new GetApplicablePriceUseCase(repository);
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        when(repository.findApplicablePrices(35455L, 1L, date))
                .thenReturn(Collections.emptyList());
        assertThrows(PriceNotFoundException.class, () -> useCase.execute(35455L, 1L, date));
    }
}
