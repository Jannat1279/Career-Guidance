package com.example.CareerGuidance.CareerGuidance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeRequests()
//	                .requestMatchers("/**").permitAll()  // Allow access to all pages without authentication
//	            .and()
//	            .csrf().disable();  // Disable CSRF for simplicity in development
//
//	        return http.build();
//	    }
//	}

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for form login
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/","/login.html", "/register.html", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                     // ensure POST to /login.html is allowed
                    .requestMatchers("/front.html").authenticated() // Ensure access to front page is restricted
                    .anyRequest().authenticated() // Protect other URLs)
                )
            .formLogin(form -> form
                    .loginPage("/login.html") // Specify custom login page
                    .loginProcessingUrl("/login") // Handle login POST requests to /login.html
                    .permitAll() // Allow everyone to access login
                    .defaultSuccessUrl("/front.html",true)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // important for login validation
    }
}

