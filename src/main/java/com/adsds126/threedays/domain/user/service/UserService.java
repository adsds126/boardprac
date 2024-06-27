package com.adsds126.threedays.domain.user.service;

import com.adsds126.threedays.domain.user.dto.UserDto;
import com.adsds126.threedays.domain.user.entity.User;
import com.adsds126.threedays.domain.user.repository.UserRepository;
import com.adsds126.threedays.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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
    @Transactional
    public String login(UserDto.Login login) {
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(user.getEmail());
    }



}
