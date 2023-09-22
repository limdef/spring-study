package com.example.study.admin;

import com.example.study.entity.User;
import com.example.study.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public void create(String name, String password) {
        User user = User.builder()
                .name(name)
                .password(password)
                .authority(User.Authority.USER)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void update(String name, String password) {
        User user = userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
        user.setPassword(password);
        userRepository.save(user);
    }

    public User get(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }
}
