package org.diegodev.hotelalurabackend.auth;

import org.diegodev.hotelalurabackend.auth.filters.JwtAuthenticationFilter;
import org.diegodev.hotelalurabackend.auth.filters.JwtValidationFilter;
import org.diegodev.hotelalurabackend.models.enums.RoleTypes;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasRole(RoleTypes.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole(RoleTypes.ADMIN.getRoleName(), RoleTypes.USER.getRoleName())
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole(RoleTypes.ADMIN.getRoleName(), RoleTypes.USER.getRoleName())
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole(RoleTypes.ADMIN.getRoleName())
                        .anyRequest().authenticated())
                .addFilter(getJwtAuthenticationFilter())
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    private JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager());
        authFilter.setFilterProcessesUrl("/api/v1/auth/signin");
        return authFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
