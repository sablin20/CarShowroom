package ru.sablin.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sablin.carshowroom.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}