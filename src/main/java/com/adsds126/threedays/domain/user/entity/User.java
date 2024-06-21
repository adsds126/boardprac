package com.adsds126.threedays.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;
}
