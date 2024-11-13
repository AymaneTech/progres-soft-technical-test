package com.progressoft.technicaltest.service;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.entity.Deal;
import com.progressoft.technicaltest.exception.ToCurrencyAndFromCurrencyAreSame;
import com.progressoft.technicaltest.mapper.DealMapper;
import com.progressoft.technicaltest.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealResponseDto save(DealRequestDto dto) {
        if (dto.toCurrency().equals(dto.fromCurrency()))
            throw new ToCurrencyAndFromCurrencyAreSame("You can't save deal with same from and to currency");

        Deal savedDeal = dealRepository.save(dealMapper.toEntity(dto));
        return dealMapper.toResponseEntity(savedDeal);
    }
}
