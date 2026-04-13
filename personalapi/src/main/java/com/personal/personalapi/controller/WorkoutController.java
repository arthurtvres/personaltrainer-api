package com.personal.personalapi.controller;

import com.personal.personalapi.dto.WorkoutDTO;
import com.personal.personalapi.model.Workout;
import com.personal.personalapi.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<Workout> create(@Valid  @RequestBody WorkoutDTO workoutDTO) {
        Workout workout = workoutService.save(workoutDTO);
        return ResponseEntity.status(201).body(workout);
    }

    @GetMapping
    public List<Workout> list() {
        return workoutService.listAll();
    }

    @GetMapping("/{id}")
    public Workout findById(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Workout> findAllByUserId(@PathVariable Long userId) {
        return workoutService.findAllByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
