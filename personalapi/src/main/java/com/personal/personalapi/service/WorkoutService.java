package com.personal.personalapi.service;

import com.personal.personalapi.dto.WorkoutDTO;
import com.personal.personalapi.exception.ResourceNotFoundException;
import com.personal.personalapi.model.Workout;
import com.personal.personalapi.model.User;
import com.personal.personalapi.repository.WorkoutRepository;
import com.personal.personalapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public List<Workout> listAll() {
        return workoutRepository.findAll();
    }

    public Workout save(WorkoutDTO workoutDTO) {
        User user = userRepository.findById(workoutDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

        Workout workout = new Workout();
        workout.setName(workoutDTO.getName());
        workout.setDescription(workoutDTO.getDescription());
        workout.setUser(user);

        return workoutRepository.save(workout);
    }

    public Workout findById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treino nao encontrado"));
    }

    public List<Workout> findAllByUserId(Long userId) {
        return workoutRepository.findAllByUserId(userId);
    }

    public void delete(Long id) {
        workoutRepository.deleteById(id);
    }

}
