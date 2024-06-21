package com.adsds126.threedays.domain.user.service;

import com.adsds126.threedays.domain.user.dto.UserDto;
import com.adsds126.threedays.domain.user.entity.User;
import com.adsds126.threedays.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User Signup(UserDto.Signup signup){
        if (userRepository.findByEmail(signup.getEmail()).isPresent()) {
            throw new RuntimeException("이미 회원가입되어있는 회원입니다.");
        }
        User newUser = User.builder()
                .username(signup.getUsername())
                .email(signup.getEmail())
                .password(passwordEncoder.encode(signup.getPassword()))
                .nickname(signup.getNickname())
                .build();
        return userRepository.save(newUser);
    }



}
