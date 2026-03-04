package com.personal.personalapi.service;

import com.personal.personalapi.dto.ExercicioDTO;
import com.personal.personalapi.model.Exercicio;
import com.personal.personalapi.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public List<Exercicio> listarExercicios() {
        return exercicioRepository.findAll();
    }

    public Exercicio salvarExercicio(ExercicioDTO exercicioDTO) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(exercicioDTO.getNome());
        exercicio.setDescricao(exercicioDTO.getDescricao());
        exercicio.setGifurl(exercicioDTO.getGifurl());
        return exercicioRepository.save(exercicio);
    }
}
