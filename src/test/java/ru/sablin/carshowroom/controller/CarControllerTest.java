package ru.sablin.carshowroom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sablin.carshowroom.entity.Car;
import ru.sablin.carshowroom.repository.CarRepository;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @MockBean
    CarRepository repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getById() throws Exception {
        var mockCar = Car.builder()
                .id(15L)
                .model("AUDI-Q8")
                .speed(370L)
                .price(BigDecimal.valueOf(22_000_000))
                .build();

        when(repository.findById(15L)).thenReturn(mockCar);

        mockMvc.perform(get("/api/v1/cars/")
                    .param("id", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value(mockCar.getModel()))
                .andExpect(jsonPath("$.speed").value(mockCar.getSpeed()))
                .andExpect(jsonPath("$.price").value(mockCar.getPrice()));

        verify(repository, times(1)).findById(mockCar.getId());
    }

    @Test
    void create() throws Exception {
        var mockCar = Car.builder()
                .id(15L)
                .model("AUDI-Q8")
                .speed(370L)
                .price(BigDecimal.valueOf(22_000_000))
                .build();

        mockMvc.perform(post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockCar)))
                .andExpect(status().isOk());

        verify(repository, times(1)).create(mockCar);
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/api/v1/cars/")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        var updateCar = Car.builder()
                .id(1L)
                .model("Nissan")
                .speed(200L)
                .price(BigDecimal.valueOf(2_000_000))
                .build();

        mockMvc.perform(put("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateCar)))
                .andExpect(status().isOk());
    }
}