package com.personal.personalapi.service;

import com.personal.personalapi.dto.ExerciseDTO;
import com.personal.personalapi.exception.ResourceNotFoundException;
import com.personal.personalapi.model.Exercise;
import com.personal.personalapi.model.Workout;
import com.personal.personalapi.repository.ExerciseRepository;
import com.personal.personalapi.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<Exercise> listAll() {
        return exerciseRepository.findAll();
    }

    public Exercise save(ExerciseDTO exerciseDTO) {
        Workout workout = workoutRepository.findById(exerciseDTO.getWorkoutId())
                .orElseThrow(() -> new ResourceNotFoundException("Treino nao encontrado"));

        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setSets(exerciseDTO.getSets());
        exercise.setReps(exerciseDTO.getReps());
        exercise.setWorkout(workout);

        return exerciseRepository.save(exercise);
    }

    public Exercise update(Long id, ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercicio nao encontrado"));

        Workout workout = workoutRepository.findById(exerciseDTO.getWorkoutId())
                .orElseThrow(() -> new ResourceNotFoundException("Treino nao encontrado"));

        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setSets(exerciseDTO.getSets());
        exercise.setReps(exerciseDTO.getReps());
        exercise.setWorkout(workout);

        return exerciseRepository.save(exercise);
    }

    public Exercise findById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercicio nao encontrado"));
    }

    public List<Exercise> findAllByWorkoutId(Long workoutId) {
        return exerciseRepository.findAllByWorkoutId(workoutId);
    }

    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }

}
