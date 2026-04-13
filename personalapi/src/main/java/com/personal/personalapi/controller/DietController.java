package com.personal.personalapi.controller;

import com.personal.personalapi.dto.DietDTO;
import com.personal.personalapi.model.Diet;
import com.personal.personalapi.service.DietService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diets")
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping
    public ResponseEntity<Diet> create(@Valid @RequestBody DietDTO dietDTO) {
        Diet diet = dietService.save(dietDTO);
        return ResponseEntity.status(201).body(diet);
    }

    @GetMapping
    public List<Diet> list() {
        return dietService.listAll();
    }

    @GetMapping("/{id}")
    public Diet findById(@PathVariable Long id) {
        return dietService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public Diet findByUserId(@PathVariable Long userId) {
        return dietService.findByUserId(userId);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Diet> update(@PathVariable Long id, @Valid @RequestBody DietDTO dietDTO) {
        Diet diet = dietService.update(id, dietDTO);
        return ResponseEntity.ok(diet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dietService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
