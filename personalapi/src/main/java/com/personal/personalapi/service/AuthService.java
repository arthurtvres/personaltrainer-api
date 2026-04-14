package com.personal.personalapi.service;

import com.personal.personalapi.dto.LoginRequestDTO;
import com.personal.personalapi.dto.LoginResponseDTO;
import com.personal.personalapi.exception.BusinessException;
import com.personal.personalapi.model.User;
import com.personal.personalapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new BusinessException("Email ou senha inválidos");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new LoginResponseDTO(token);
    }
}
