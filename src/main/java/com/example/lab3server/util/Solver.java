package com.example.lab3server.util;

import com.example.lab3server.dto.IntegralResponseDto;

import java.util.Arrays;
import java.util.function.Function;

public class Solver {
    //1 - Метод левых прямоугольников
    public static IntegralResponseDto method1(double a, double b, double eps, Function<Double, Double> f) {
        int n = 4;
        double h;
        double[] y;
        double currentS;
        double lastS = Double.MAX_VALUE;
        int iteration = 0;
        while (true) {
            iteration++;
            h = (b - a) / n;
            y = new double[n];
            for (int i = 0; i < n; i++) {
                double x = a + i * h;
                y[i] = f.apply(x);
            }
            currentS = Arrays.stream(y).sum() * h;
            if (countAccuracy(lastS, currentS, 2, eps)) {
                break;
            }

            lastS = currentS;
            n *= 2;
            if (n > 10000000) {
                throw new IllegalArgumentException("Прошло слишком много итераций, поменяйте входные данные и попробуйте еще раз");
            }
        }
        return new IntegralResponseDto(currentS, n);
    }

    //2 - Метод средних прямоугольников
    public static IntegralResponseDto method2(double a, double b, double eps, Function<Double, Double> f) {
        int n = 4;
        double h;
        double[] y;
        double currentS;
        double lastS = Double.MAX_VALUE;
        int iteration = 0;
        while (true) {
            iteration++;
            h = (b - a) / n;
            y = new double[n];
            for (int i = 0; i < n; i++) {
                double x = a + (i + 0.5) * h;
                y[i] = f.apply(x);
            }
            currentS = Arrays.stream(y).sum() * h;
            if (countAccuracy(lastS, currentS, 2, eps)) {
                break;
            }

            lastS = currentS;
            n *= 2;
            if (n > 10000000) {
                throw new IllegalArgumentException("Прошло слишком много итераций, поменяйте входные данные и попробуйте еще раз");
            }
        }
        return new IntegralResponseDto(currentS, n);
    }

    //3 - Метод правых прямоугольников
    public static IntegralResponseDto method3(double a, double b, double eps, Function<Double, Double> f) {
        int n = 4;
        double h;
        double[] y;
        double currentS;
        double lastS = Double.MAX_VALUE;
        int iteration = 0;
        while (true) {
            iteration++;
            h = (b - a) / n;
            y = new double[n];
            for (int i = 0; i < n; i++) {
                double x = a + (i + 1) * h;
                y[i] = f.apply(x);
            }
            currentS = Arrays.stream(y).sum() * h;
            if (countAccuracy(lastS, currentS, 2, eps)) {
                break;
            }

            lastS = currentS;
            n *= 2;
            if (n > 10000000) {
                throw new IllegalArgumentException("Прошло слишком много итераций, поменяйте входные данные и попробуйте еще раз");
            }
        }
        return new IntegralResponseDto(currentS, n);
    }

    //4 – Метод трапеций
    public static IntegralResponseDto method4(double a, double b, double eps, Function<Double, Double> f) {
        int n = 4;
        double h;
        double[] y;
        double currentS;
        double lastS = Double.MAX_VALUE;
        int iteration = 0;
        while (true) {
            iteration++;
            h = (b - a) / n;
            y = new double[n + 1];
            for (int i = 0; i < n + 1; i++) {
                double x = a + i * h;
                y[i] = f.apply(x);
            }
            currentS = ((y[0] + y[n]) / 2 + Arrays.stream(Arrays.copyOfRange(y, 1, n - 1)).sum()) * h;
            if (countAccuracy(lastS, currentS, 2, eps)) {
                break;
            }

            lastS = currentS;
            n *= 2;
            if (n > 10000000) {
                throw new IllegalArgumentException("Прошло слишком много итераций, поменяйте входные данные и попробуйте еще раз");
            }
        }
        return new IntegralResponseDto(currentS, n);
    }

    // 5 -Метод Симпсона
    public static IntegralResponseDto method5(double a, double b, double eps, Function<Double, Double> f) {
        int n = 4;
        double h;
        double[] y;
        double currentS;
        double lastS = Double.MAX_VALUE;
        int iteration = 0;
        while (true) {
            iteration++;
            h = (b - a) / n;
            y = new double[n + 1];
            for (int i = 0; i < n + 1; i++) {
                double x = a + i * h;
                y[i] = f.apply(x);
            }
            currentS = calculateYSum(y, n) * h / 3;
            if (countAccuracy(lastS, currentS, 4, eps)) {
                break;
            }

            lastS = currentS;
            n *= 2;
            if (n > 10000000) {
                throw new IllegalArgumentException("Прошло слишком много итераций, поменяйте входные данные и попробуйте еще раз");
            }
        } 
        return new IntegralResponseDto(currentS, n);
    }

    private static boolean countAccuracy(double lastS, double currentS, int k, double eps) {
        return (Math.abs((lastS - currentS)) / (Math.pow(2, k) - 1)) <= eps;
    }

    private static double calculateYSum(double[] y, int n) {
        double odd = 0;
        for (int i = 1; i < y.length - 1; i += 2) {
            odd += y[i];
        }
        double even = 0;
        for (int i = 2; i < y.length - 1; i += 2) {
            even += y[i];
        }
        return y[0] + y[n] + 4 * odd + 2 * even;
    }
}
