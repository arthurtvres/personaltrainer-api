package com.personal.personalapi.controller;

import com.personal.personalapi.dto.ExerciseDTO;
import com.personal.personalapi.model.Exercise;
import com.personal.personalapi.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseService.save(exerciseDTO);
        return ResponseEntity.status(201).body(exercise);
    }

    @GetMapping
    public List<Exercise> list() {
        return exerciseService.listAll();
    }

    @GetMapping("/{id}")
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @GetMapping("/workout/{workoutId}")
    public List<Exercise> findAllByWorkoutId(@PathVariable Long workoutId) {
        return exerciseService.findAllByWorkoutId(workoutId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable Long id, @Valid @RequestBody ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseService.update(id, exerciseDTO);
        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
