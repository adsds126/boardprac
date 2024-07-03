package com.adsds126.threedays.domain.board.service;

import com.adsds126.threedays.domain.board.dto.BoardDto;
import com.adsds126.threedays.domain.board.entity.Board;
import com.adsds126.threedays.domain.board.repository.BoardRepository;
import com.adsds126.threedays.domain.user.entity.User;
import com.adsds126.threedays.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Board post(BoardDto.PostDto postDto, UserDetails userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Board board = Board.builder()
                .title(postDto.getTitle())
                .topic(postDto.getTopic())
                .author(user)
                .content(postDto.getContent())
                .build();
        return boardRepository.save(board);
    }

    @Transactional
    public BoardDto.Response getBoard(long boardId, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Board board = boardRepository.findById(boardId).orElse(null);
        if(board != null) {
            return new BoardDto.Response(board.getId(), board.getTopic(), board.getTitle(), board.getContent(), board.getAuthor(), board.getCreatedAt());
        } else {
            return null;
        }
    }
}
