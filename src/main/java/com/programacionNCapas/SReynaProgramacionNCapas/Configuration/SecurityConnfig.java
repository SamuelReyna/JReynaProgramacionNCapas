package com.programacionNCapas.SReynaProgramacionNCapas.Configuration;

import com.programacionNCapas.SReynaProgramacionNCapas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //establecer que es una clase de configuración
@EnableWebSecurity //habilitar la configuracion de spring security
public class SecurityConnfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/usuario").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/usuario/cargamasiva").hasRole("ADMIN")
                .requestMatchers("/usuario/form").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/usuario", true)
                .permitAll()).userDetailsService(userService);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
