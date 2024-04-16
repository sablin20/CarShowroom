package ru.sablin.carshowroom.repository;

import org.springframework.stereotype.Repository;
import ru.sablin.carshowroom.entity.Car;

@Repository
public interface CarRepository {
    Car findById(Long id);
    void delete(Long id);
    void update(Car car);
    Car create(Car car);
}