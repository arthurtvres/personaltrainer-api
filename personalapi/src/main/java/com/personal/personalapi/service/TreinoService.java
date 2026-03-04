package com.personal.personalapi.service;

import com.personal.personalapi.dto.TreinoDTO;
import com.personal.personalapi.model.Treino;
import com.personal.personalapi.model.User;
import com.personal.personalapi.repository.TreinoRepository;
import com.personal.personalapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {
    private final TreinoRepository treinoRepository;
    private final UserRepository userRepository;

    public TreinoService(TreinoRepository treinoRepository, UserRepository userRepository) {
        this.treinoRepository = treinoRepository;
        this.userRepository = userRepository;
    }

    public List<Treino> listarTreinos() {
        return treinoRepository.findAll();
    }

    public Treino salvarTreino(TreinoDTO treinoDTO) {
        User user = userRepository.findById(treinoDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Treino treino = new Treino();
        treino.setNome(treinoDTO.getNome());
        treino.setDescricao(treinoDTO.getDescricao());
        treino.setAluno(user);

        return treinoRepository.save(treino);
    }

}
