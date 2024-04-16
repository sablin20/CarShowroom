package ru.sablin.carshowroom.repositoryImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sablin.carshowroom.entity.Car;
import ru.sablin.carshowroom.repository.CarRepository;

@Data
@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    @Cacheable("cars")
    @Override
    public Car findById(Long id) {
        return jdbcTemplate.queryForObject("""
                SELECT id,
                       model,
                       speed,
                       price
                FROM car
                WHERE id = ?
                """, (rs, rowNum) ->
                Car.builder()
                    .id(rs.getLong("id"))
                    .model(rs.getString("model"))
                    .speed(rs.getLong("speed"))
                    .price(rs.getBigDecimal("price"))
                    .build(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM car WHERE id = ?", id);
    }

    @Override
    public void update(Car car) {
        jdbcTemplate.update("""
                                UPDATE car
                                SET id = ?,
                                    model = ?,
                                    speed = ?,
                                    price = ?
                                WHERE id = ?
                                """, car.getId(),
                                     car.getModel(),
                                     car.getSpeed(),
                                     car.getPrice(), car.getId());
    }

    @Override
    public Car create(Car car) {
        jdbcTemplate.update("""
                                INSERT INTO car
                                VALUES (?,?,?,?)
                                """, car.getId(),
                                     car.getModel(),
                                     car.getSpeed(),
                                     car.getPrice());

        return jdbcTemplate.queryForObject("""
                SELECT id,
                       model,
                       speed,
                       price
                FROM Car
                WHERE id = ?
                """, (rs, rowNum) ->
                Car.builder()
                        .id(rs.getLong("id"))
                        .model(rs.getString("model"))
                        .speed(rs.getLong("speed"))
                        .price(rs.getBigDecimal("price"))
                        .build(),
                car.getId());
    }
}