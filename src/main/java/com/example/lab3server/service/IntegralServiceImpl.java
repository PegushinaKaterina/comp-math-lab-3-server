package com.example.lab3server.service;

import com.example.lab3server.dto.IntegralRequestDto;
import com.example.lab3server.dto.IntegralResponseDto;
import com.example.lab3server.util.Solver;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IntegralServiceImpl implements IntegralService {
    @Override
    public IntegralResponseDto solve(IntegralRequestDto integralRequestDto) {
        Function<Double, Double> f;
        Double a = integralRequestDto.getLeft();
        Double b = integralRequestDto.getRight();
        Double eps = integralRequestDto.getEps();
        if (integralRequestDto.getIntegral() == 1) {
            f = (x) -> (4 * x * x * x - 5 * x * x + 6 * x - 7);
        } else if (integralRequestDto.getIntegral() == 2) {
            f = (x) -> (Math.sin(2 * x) * Math.sin(2 * x) / 7);
        } else {
            f = (x) -> (-x * x * x - x * x + x + 3);
        }

        if (integralRequestDto.getMethod() == 1) {
            return Solver.method1(a, b, eps, f);
        } else if (integralRequestDto.getMethod() == 2) {
            return Solver.method2(a, b, eps, f);
        } else if (integralRequestDto.getMethod() == 3) {
            return Solver.method3(a, b, eps, f);
        } else if (integralRequestDto.getMethod() == 4) {
            return Solver.method4(a, b, eps, f);
        } else {
            return Solver.method5(a, b, eps, f);
        }
    }
}

