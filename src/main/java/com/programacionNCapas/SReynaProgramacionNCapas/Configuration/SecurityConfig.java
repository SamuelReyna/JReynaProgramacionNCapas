package com.programacionNCapas.SReynaProgramacionNCapas.Configuration;

import com.programacionNCapas.SReynaProgramacionNCapas.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration //establecer que es una clase de configuración
@EnableWebSecurity //habilitar la configuracion de spring security
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/usuario").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/usuario/cargamasiva").hasRole("ADMIN")
                .requestMatchers("/usuario/form").hasRole("ADMIN")
                .requestMatchers("/usuario/encriptar").hasRole("ADMIN")
                .requestMatchers("/login").permitAll()
                .requestMatchers("/forgot-password").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/changePassword").permitAll()
                .requestMatchers("/changePass").permitAll()
                .requestMatchers("/sendEmail").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/usuario", true)
                .failureHandler((request, response, exception) -> {
                    String errorParam = "";

                    if (exception instanceof DisabledException) {
                        errorParam = "disabled";
                    } else if (exception instanceof BadCredentialsException) {
                        errorParam = "bad_credentials";
                    } else {
                        errorParam = "unknown";
                    }

                    String redirectUrl = request.getContextPath() + "/login?error=" + errorParam;
                    response.sendRedirect(redirectUrl);
                })
                .permitAll())
                .userDetailsService(userService);

        return http.build();
    }

}
