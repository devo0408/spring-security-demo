package com.devo.product.security;

import com.devo.product.domain.security.Authority;
import com.devo.product.domain.security.UserCredentials;
import com.devo.product.repositories.security.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserCredentialsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials userCredentials = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
             userCredentials.getUsername(),
             userCredentials.getPassword(),
             userCredentials.getEnabled(),
             userCredentials.getAccountNonExpired(),
             userCredentials.getCredentialsNonExpired(),
             userCredentials.getAccountNonLocked(),
             convertToSpringAuthorities(userCredentials.getAuthorities())
        );
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        return Optional.ofNullable(authorities)
                .stream()
                .flatMap(Collection::stream)
                .map(Authority::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(toSet());
    }
}
