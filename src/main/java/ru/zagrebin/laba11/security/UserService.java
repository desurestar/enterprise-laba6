package ru.zagrebin.laba11.security;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRecord rec = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Невозможно найти пользователя " + username));

        return new org.springframework.security.core.userdetails.User(
                rec.getUsername(),
                rec.getPassword(),
                Collections.singleton(new org.springframework.security.core.authority.SimpleGrantedAuthority(rec.getRole()))
        );
    }
}
