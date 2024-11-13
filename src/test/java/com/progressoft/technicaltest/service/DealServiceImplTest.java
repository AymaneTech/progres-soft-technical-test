package com.progressoft.technicaltest.service;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.entity.Deal;
import com.progressoft.technicaltest.exception.DuplicateDealIdException;
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

    private DealService underTest;

    private DealRequestDto dealRequestDto;
    private Deal deal;

    @BeforeEach
    void setup() {
        underTest = new DealServiceImpl(dealRepository, dealMapper);
        dealRequestDto = new DealRequestDto("deal123",
                Currency.getInstance("MAD"),
                Currency.getInstance("USD"),
                LocalDateTime.now(),
                BigDecimal.valueOf(2000)
        );

        deal = new Deal(dealRequestDto.id(),
                dealRequestDto.fromCurrency(),
                dealRequestDto.toCurrency(),
                dealRequestDto.timestamp(),
                dealRequestDto.amount());
    }

    @Test
    void givenValidRequest_whenSave_thenReturnCreatedDeal() {
        given(dealMapper.toEntity(dealRequestDto)).willReturn(deal);
        given(dealRepository.save(any(Deal.class))).willReturn(deal);
        given(dealMapper.toResponseEntity(deal))
                .willReturn(new DealResponseDto(dealRequestDto.id(),
                        dealRequestDto.fromCurrency(),
                        dealRequestDto.toCurrency(),
                        dealRequestDto.timestamp(),
                        dealRequestDto.amount()));

        DealResponseDto actual = underTest.save(dealRequestDto);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(deal.getId());
        verify(dealRepository).save(any(Deal.class));
    }

    @Test
    void givenDealIdAlreadyExists_whenSave_thenThrowDuplicatedDealIdException() {
        given(dealRepository.existsById("existent-id")).willReturn(true);

        assertThatExceptionOfType(DuplicateDealIdException.class)
                .isThrownBy(() -> underTest.save(dealRequestDto))
                .withMessage("Deal id already exists");
    }
}