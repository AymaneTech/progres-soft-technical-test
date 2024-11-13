package com.progressoft.technicaltest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "deals")

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class Deal {

    @Id
    private String id;

    @NotNull
    private Currency fromCurrency;

    @NotNull
    private Currency toCurrency;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private BigDecimal amount;
}
