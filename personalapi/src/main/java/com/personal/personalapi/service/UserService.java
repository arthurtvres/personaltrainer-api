package com.personal.personalapi.service;

import com.personal.personalapi.dto.UserDTO;
import com.personal.personalapi.exception.BusinessException;
import com.personal.personalapi.exception.ResourceNotFoundException;
import com.personal.personalapi.model.User;
import com.personal.personalapi.repository.DietRepository;
import com.personal.personalapi.repository.UserRepository;
import com.personal.personalapi.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final DietRepository dietRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, WorkoutRepository workoutRepository, DietRepository dietRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.dietRepository = dietRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
    }

    public void deleteUser(Long id) {
        System.out.println("Entrou no deleteUser");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

        boolean hasWorkouts = workoutRepository.existsByUserId(id);
        boolean hasDiets = dietRepository.existsByUserId(id);

        if (hasWorkouts || hasDiets) {
            System.out.println("Usuario possui treinos ou dietas associadas, nao e possivel excluir.");
            throw new BusinessException("Nao e possivel excluir o usuario, pois ele possui treinos ou dietas associadas.");
        }

        userRepository.deleteById(id);
    }



}
