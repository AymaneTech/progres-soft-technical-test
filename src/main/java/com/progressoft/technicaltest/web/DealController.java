package com.progressoft.technicaltest.web;

import com.progressoft.technicaltest.dto.DealRequestDto;
import com.progressoft.technicaltest.dto.DealResponseDto;
import com.progressoft.technicaltest.service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<DealResponseDto> save(@RequestBody @Valid DealRequestDto dto) {
        DealResponseDto deal = dealService.save(dto);

        return new ResponseEntity<>(deal, HttpStatus.CREATED);
    }
}
