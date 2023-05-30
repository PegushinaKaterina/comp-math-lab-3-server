package com.example.lab3server.service;

import com.example.lab3server.dto.IntegralRequestDto;
import com.example.lab3server.dto.IntegralResponseDto;


public interface IntegralService {
    IntegralResponseDto solve(IntegralRequestDto integralRequestDto);
}
