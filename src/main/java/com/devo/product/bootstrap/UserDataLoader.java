package com.devo.product.bootstrap;

import com.devo.product.domain.security.Authority;
import com.devo.product.domain.security.UserCredentials;
import com.devo.product.repositories.security.AuthorityRepository;
import com.devo.product.repositories.security.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("USER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

        userCredentialsRepository.save(UserCredentials.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(Set.of(admin))
                .build());

        userCredentialsRepository.save(UserCredentials.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities(Set.of(userRole))
                .build());

        userCredentialsRepository.save(UserCredentials.builder()
                .username("devo")
                .password(passwordEncoder.encode("devo"))
                .authorities(Set.of(customer))
                .build());

        log.debug("Users Loaded: " + userCredentialsRepository.count());
    }

}
