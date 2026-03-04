package com.personal.personalapi.controller;

import com.personal.personalapi.dto.TreinoDTO;
import com.personal.personalapi.model.Treino;
import com.personal.personalapi.service.TreinoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treino")
public class TreinoController {
    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public Treino criarTreino(@RequestBody TreinoDTO treinoDTO) {
        return treinoService.salvarTreino(treinoDTO);
    }

    @GetMapping
    public List<Treino> listarTreino() {
        return treinoService.listarTreinos();
    }
}
