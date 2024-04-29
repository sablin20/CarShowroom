package ru.sablin.carshowroom.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sablin.carshowroom.entity.Car;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    @PersistenceContext
    private final EntityManager entityManager;

    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    public void create(Car car) {
        var carN = new Car();
        carN.setId(car.getId());
        carN.setSpeed(car.getSpeed());
        carN.setPrice(car.getPrice());
        carN.setModel(car.getModel());
        entityManager.persist(carN);
    }

    public void deleteById(Long id) {
        var car = entityManager.find(Car.class, id);
        entityManager.remove(car);
    }

    public void update(Car car) {
        var updateCar = entityManager.find(Car.class, car.getId());
        updateCar.setModel(car.getModel());
        updateCar.setPrice(car.getPrice());
        updateCar.setSpeed(car.getSpeed());
        entityManager.persist(updateCar);
    }
}