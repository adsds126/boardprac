package com.adsds126.threedays.domain.board.dto;


import com.adsds126.threedays.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class BoardDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDto {
        private Enum topic;
        private String title;
        private String content;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private Enum topic;
        private String title;
        private String content;
        private User author;
        private LocalDateTime createdAt;
    }

}
