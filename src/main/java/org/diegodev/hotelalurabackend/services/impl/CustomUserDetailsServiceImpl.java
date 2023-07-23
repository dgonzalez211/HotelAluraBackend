package org.diegodev.hotelalurabackend.services.impl;

import org.diegodev.hotelalurabackend.config.CustomUserDetails;
import org.diegodev.hotelalurabackend.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<org.diegodev.hotelalurabackend.models.entities.User> o = repository
                .getUserByUsername(username);

        if (o.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s does not exists!", username));
        }
        org.diegodev.hotelalurabackend.models.entities.User user = o.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                o.get().getId(),
                true,
                true,
                true,
                true,
                authorities);

    }

}
