package ru.sablin.carshowroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sablin.carshowroom.entity.Car;
import ru.sablin.carshowroom.repository.CarRepository;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarRepository repository;

    @GetMapping("/")
    public Car getById(@RequestParam("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public Car create(@RequestBody Car car) {
        return repository.create(car);
    }

    @DeleteMapping("/")
    public void remove(@RequestParam("id") Long id) {
        repository.delete(id);
    }

    @PutMapping("/")
    public void update(@RequestBody Car car) {
        repository.update(car);
    }
}