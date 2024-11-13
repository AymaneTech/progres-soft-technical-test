package com.progressoft.technicaltest.dto;

import com.progressoft.technicaltest.entity.Deal;
import com.progressoft.technicaltest.validation.UniqueField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record DealRequestDto(
        @NotBlank @UniqueField(entityClass = Deal.class, fieldName = "id", message = "Deal Id already exists") String id,
        @NotNull Currency fromCurrency,
        @NotNull Currency toCurrency,
        @NotNull LocalDateTime timestamp,
        @NotNull @Positive BigDecimal amount
) {
}
