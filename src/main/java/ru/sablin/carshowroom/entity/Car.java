package ru.sablin.carshowroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Car {
    private Long id;
    private String model;
    private Long speed;
    private BigDecimal price;
}