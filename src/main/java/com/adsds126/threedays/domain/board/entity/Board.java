package com.adsds126.threedays.domain.board.entity;

import com.adsds126.threedays.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Enum topic;

    @Column
    private String title;

    @Column
    private String content;

    @OneToOne
    private User author;

    @Column
    private LocalDateTime createdAt;
}
