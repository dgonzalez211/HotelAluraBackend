package org.diegodev.hotelalurabackend.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.diegodev.hotelalurabackend.models.entities.User;
import org.diegodev.hotelalurabackend.models.enums.RoleTypes;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.diegodev.hotelalurabackend.auth.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        User user;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword();

        } catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {

        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal())
                .getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        boolean isAdmin = roles.stream().anyMatch(r -> r.getAuthority().equals(RoleTypes.ADMIN.getRoleName()));
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        claims.put("isAdmin", isAdmin);
        claims.put("username", username);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("message", String.format("Welcome %s, you have logged in with success!", username));
        body.put("username", username);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Auth failed, incorrect username or password!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
    }

}
