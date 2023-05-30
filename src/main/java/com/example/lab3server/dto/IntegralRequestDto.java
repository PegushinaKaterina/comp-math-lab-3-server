package com.example.lab3server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IntegralRequestDto {
    private Integer integral;
    private Double left;
    private Double right;
    private Double eps;
    private Integer method;

}
