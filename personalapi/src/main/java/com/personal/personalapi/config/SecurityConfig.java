package com.personal.personalapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**"
                        ).permitAll()

                        // USERS
                        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("PERSONAL")

                        // WORKOUTS
                        .requestMatchers(HttpMethod.GET, "/workouts/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/workouts").hasRole("PERSONAL")
                        .requestMatchers(HttpMethod.DELETE, "/workouts/**").hasRole("PERSONAL")

                        // EXERCISES
                        .requestMatchers(HttpMethod.GET, "/exercises/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/exercises").hasRole("PERSONAL")
                        .requestMatchers(HttpMethod.PUT, "/exercises/**").hasRole("PERSONAL")
                        .requestMatchers(HttpMethod.DELETE, "/exercises/**").hasRole("PERSONAL")

                        // DIETS
                        .requestMatchers(HttpMethod.GET, "/diets/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/diets").hasRole("PERSONAL")
                        .requestMatchers(HttpMethod.PUT, "/diets/**").hasRole("PERSONAL")
                        .requestMatchers(HttpMethod.DELETE, "/diets/**").hasRole("PERSONAL")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
