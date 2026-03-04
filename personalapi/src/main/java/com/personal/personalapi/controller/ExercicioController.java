package com.personal.personalapi.controller;

import com.personal.personalapi.dto.ExercicioDTO;
import com.personal.personalapi.model.Exercicio;
import com.personal.personalapi.service.ExercicioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @PostMapping
    public Exercicio criar(@RequestBody ExercicioDTO exercicioDTO) {
        return exercicioService.salvarExercicio(exercicioDTO);
    }

    @GetMapping
    public List<Exercicio> listarExercicios() {
        return exercicioService.listarExercicios();
    }
}
