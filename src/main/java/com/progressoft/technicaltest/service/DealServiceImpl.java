package com.progressoft.technicaltest.service;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.entity.Deal;
import com.progressoft.technicaltest.exception.DuplicateDealIdException;
import com.progressoft.technicaltest.mapper.DealMapper;
import com.progressoft.technicaltest.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealResponseDto save(DealRequestDto dto) {
        log.info("Attempting to save deal with ID: {}", dto.id());

        if (dealRepository.existsById(dto.id())) {
            log.warn("Duplicate deal ID detected: {}. Operation aborted.", dto.id());
            throw new DuplicateDealIdException("Deal id already exists");
        }

        Deal savedDeal = dealRepository.save(dealMapper.toEntity(dto));
        log.info("Deal saved successfully with ID: {}", savedDeal.getId());

        return dealMapper.toResponseEntity(savedDeal);
    }
}
