package com.adsds126.threedays.domain.user.controller;

import com.adsds126.threedays.domain.user.dto.UserDto;
import com.adsds126.threedays.domain.user.entity.User;
import com.adsds126.threedays.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody UserDto.Signup signup){
            User newUser = userService.Signup(signup);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto.Login login) {
        String token = userService.login(login);
        return ResponseEntity.ok(token);
    }
}
