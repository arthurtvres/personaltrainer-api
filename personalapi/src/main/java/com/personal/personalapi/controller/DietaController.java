package com.personal.personalapi.controller;

import com.personal.personalapi.dto.DietaDTO;
import com.personal.personalapi.model.Dieta;
import com.personal.personalapi.service.DietaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dieta")
public class DietaController {

    private final DietaService dietaService;

    public DietaController(DietaService dietaService) {
        this.dietaService = dietaService;
    }

    @PostMapping
    public Dieta criarDieta(@RequestBody DietaDTO dietaDTO) {
        return dietaService.salvarDieta(dietaDTO);
    }

    @GetMapping
    public List<Dieta> listarDieta() {
        return dietaService.listarDietas();
    }

}
