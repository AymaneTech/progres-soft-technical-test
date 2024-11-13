package com.progressoft.technicaltest.mapper;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {
    Deal toEntity(DealRequestDto dto);

    DealResponseDto toResponseEntity(Deal deal);
}
