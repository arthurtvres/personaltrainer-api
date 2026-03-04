package com.personal.personalapi.service;

import com.personal.personalapi.dto.DietaDTO;
import com.personal.personalapi.model.Dieta;
import com.personal.personalapi.model.User;
import com.personal.personalapi.repository.DietaRepository;
import com.personal.personalapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietaService {
    private final DietaRepository dietaRepository;
    private final UserRepository userRepository;

    public DietaService(DietaRepository dietaRepository, UserRepository userRepository) {
        this.dietaRepository = dietaRepository;
        this.userRepository = userRepository;
    }

    public List<Dieta> listarDietas() {
        return dietaRepository.findAll();
    }

    public Dieta salvarDieta(DietaDTO dietaDTO) {
        User user = userRepository.findById(dietaDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Dieta dieta = new Dieta();
        dieta.setDescricao(dietaDTO.getDescricao());
        dieta.setAluno(user);

        return dietaRepository.save(dieta);
    }
}
