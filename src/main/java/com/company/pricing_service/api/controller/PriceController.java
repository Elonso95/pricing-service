package com.company.pricing_service.api.controller;

import com.company.pricing_service.api.dto.PriceResponse;
import com.company.pricing_service.application.usecase.GetApplicablePriceUseCase;
import com.company.pricing_service.domain.model.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    public PriceController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
    }

    @GetMapping
    public PriceResponse getPrice(@RequestParam Long productId, @RequestParam Long brandId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        Price price = getApplicablePriceUseCase.execute(productId, brandId, applicationDate);
        return new PriceResponse(price.getProductId(), price.getBrandId(), price.getPriceList(), price.getStartDate(), price.getEndDate(), price.getPrice(), price.getCurrency());
    }
}
