package com.Crio.StayEase.StayEase.Security;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Crio.StayEase.StayEase.Entity.Role;
import com.Crio.StayEase.StayEase.Services.UserService;

import java.io.IOException;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for setting up Spring Security for the application.
 * <p>
 * This configuration class defines security filters, authorization rules, and exception handling for
 * the application. It sets up JWT authentication, configures role-based access controls, and manages
 * session policies.
 * </p>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Slf4j
public class SecurityConfiguration {

   private UserService userService;

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final DaoAuthenticationProvider authenticationProvider;
    
//     private final AccessDeniedHandler accessDeniedHandler;
    
    /**
     * Configures the HTTP security for the application.
     * <p>
     * This method sets up various security aspects including:
     * <ul>
     *     <li>Disabling CSRF protection.</li>
     *     <li>Configuring URL patterns and authorization rules.</li>
     *     <li>Setting up custom JWT authentication filter.</li>
     *     <li>Configuring logout behavior and handlers.</li>
     *     <li>Enabling stateless session management.</li>
     *     <li>Adding a custom request logging filter before the authentication filter.</li>
     * </ul>
     * </p>
     *
     * @param http the {@link HttpSecurity} object to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req

                                // Hotel Controller
                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                
                                .anyRequest().authenticated()
                )
                
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               
                .addFilterBefore(new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                            throws ServletException, IOException {
                        log.debug("Processing request: {} {}", request.getMethod(), request.getRequestURI());
                        filterChain.doFilter(request, response);
                    }
                }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

   

  

}