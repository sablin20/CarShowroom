package ru.sablin.carshowroom.repositoryImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import ru.sablin.carshowroom.entity.Car;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/drop-schema.sql", "/test-schema.sql"})
@Sql(scripts = "/test-data.sql")
class CarRepositoryImplTest {

    @Autowired
    CarRepositoryImpl repository;

    @Test
    void findById() {
        var expectedModel = "Porsche-911";
        var expectedPrice = BigDecimal.valueOf(67_000_000);
        var expectedSpeed = 350L;

        var expectedCar = repository.findById(1L);

        assertNotNull(expectedCar);
        assertEquals(expectedCar.getModel(), expectedModel);
        assertEquals(expectedCar.getSpeed(), expectedSpeed);
        assertEquals(expectedCar.getPrice(), expectedPrice);
    }

    @Test
    void delete() {
        repository.delete(1L);
        assertThrows(EmptyResultDataAccessException.class, () -> repository.findById(1L));
    }

    @Test
    void update() {
        var updateCar = Car.builder()
                .id(3L)
                .model("BMW-X5M")
                .speed(360L)
                .price(BigDecimal.valueOf(18_000_000))
                .build();

        repository.update(updateCar);

        var expectedUpdateCar = repository.findById(3L);

        assertNotNull(expectedUpdateCar);
        assertEquals(expectedUpdateCar.getModel(), updateCar.getModel());
        assertEquals(expectedUpdateCar.getPrice(), updateCar.getPrice());
        assertEquals(expectedUpdateCar.getSpeed(), updateCar.getSpeed());
    }

    @Test
    void create() {
        var car = Car.builder()
                .id(14L)
                .model("Ferrari")
                .speed(290L)
                .price(BigDecimal.valueOf(10_000_000))
                .build();

        repository.create(car);

        var expectedCar = repository.findById(14L);

        assertNotNull(expectedCar);
        assertEquals(expectedCar, car);
    }
}