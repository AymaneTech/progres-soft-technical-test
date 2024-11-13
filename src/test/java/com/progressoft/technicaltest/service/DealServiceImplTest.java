package com.progressoft.technicaltest.service;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.entity.Deal;
import com.progressoft.technicaltest.exception.CurrencyMismatchException;
import com.progressoft.technicaltest.mapper.DealMapper;
import com.progressoft.technicaltest.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;
    @Mock
    private DealMapper dealMapper;

    private DealService sut;

    @BeforeEach
    void setup() {
        sut = new DealServiceImpl(dealRepository, dealMapper);
    }

    @Test
    void givenInvalidRequest_whenSave_thenThrowCurrencyMismatchException() {
        DealRequestDto request = new DealRequestDto("deal123",
                Currency.getInstance("MAD"),
                Currency.getInstance("MAD"),
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        assertThatExceptionOfType(CurrencyMismatchException.class)
                .isThrownBy(() -> sut.save(request))
                .withMessage("You can't save deal with same from and to currency");
    }

    @Test
    void givenValidRequest_whenSave_thenReturnCreatedDeal() {
        DealRequestDto request = new DealRequestDto("deal123",
                Currency.getInstance("MAD"),
                Currency.getInstance("USD"),
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        Deal excepted = new Deal(request.id(),
                request.fromCurrency(),
                request.toCurrency(),
                request.timestamp(),
                request.amount());

        given(dealMapper.toEntity(request)).willReturn(excepted);
        given(dealRepository.save(any(Deal.class))).willReturn(excepted);
        given(dealMapper.toResponseEntity(excepted))
                .willReturn(new DealResponseDto(request.id(),
                        request.fromCurrency(),
                        request.toCurrency(),
                        request.timestamp(),
                        request.amount()));

        DealResponseDto actual = sut.save(request);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(excepted.getId());
        verify(dealRepository).save(any(Deal.class));
    }
}