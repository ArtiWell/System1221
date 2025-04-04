package com.example.system1221.controller;

import com.example.system1221.dto.DishDTO;
import com.example.system1221.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishDTO createDish(@RequestBody DishDTO dto) {
        return dishService.createDish(dto);
    }

    @GetMapping
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes();
    }
}
