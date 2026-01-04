package com.company.pricing_service.application.usecase;

import com.company.pricing_service.domain.model.Price;
import com.company.pricing_service.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetApplicablePriceUseCase {

    private final PriceRepository priceRepository;

    public GetApplicablePriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price execute(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrices(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
    }
}
