package ru.sablin.carshowroom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Car {

    @Id
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private Long speed;

    @NotNull
    private BigDecimal price;
}