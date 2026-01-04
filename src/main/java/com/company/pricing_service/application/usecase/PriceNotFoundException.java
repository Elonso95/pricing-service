package com.company.pricing_service.application.usecase;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super("No price found for product " + productId + ", brand " + brandId + " at " + applicationDate);
    }
}
