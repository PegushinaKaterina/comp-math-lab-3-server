package com.example.lab3server.controller;

import com.example.lab3server.dto.IntegralRequestDto;
import com.example.lab3server.dto.IntegralResponseDto;
import com.example.lab3server.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class IntegralController {
    private final IntegralService integralService;

    @Autowired
    public IntegralController(IntegralService integralService) {
        this.integralService = integralService;
    }

    @PostMapping("/integral")
    public ResponseEntity<IntegralResponseDto> solve(@RequestBody IntegralRequestDto integralRequestDto) {
        return ResponseEntity.ok(integralService.solve(integralRequestDto));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
