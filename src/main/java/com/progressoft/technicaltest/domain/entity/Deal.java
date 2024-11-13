package com.progressoft.technicaltest.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "deals")

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class Deal {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    private Currency fromCurrency;

    @NotNull
    private Currency toCurrency;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private Long amount;
}
