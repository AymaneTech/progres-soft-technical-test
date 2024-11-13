package com.progressoft.technicaltest.service;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;

public interface DealService {
    DealResponseDto save(DealRequestDto dto);
}
