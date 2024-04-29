package ru.sablin.carshowroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sablin.carshowroom.entity.Car;
import ru.sablin.carshowroom.service.CarService;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    @GetMapping("/")
    public Car getById(@RequestParam("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public void create(@RequestBody Car car) {
        service.create(car);
    }

    @DeleteMapping("/")
    public void remove(@RequestParam("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/")
    public void update(@RequestBody Car car) {
        service.update(car);
    }
}