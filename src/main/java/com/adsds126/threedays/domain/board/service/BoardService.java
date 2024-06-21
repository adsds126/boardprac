package com.adsds126.threedays.domain.board.service;

import com.adsds126.threedays.domain.board.dto.BoardDto;
import com.adsds126.threedays.domain.board.entity.Board;
import com.adsds126.threedays.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board post(BoardDto.PostDto postDto) {
        Board board = Board.builder()
                .title(postDto.getTitle())
                .topic(postDto.getTopic())
                .content(postDto.getContent())
                .build();
        return boardRepository.save(board);
    }

    @Transactional
    public BoardDto.Response getBoard(long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board != null) {
            return new BoardDto.Response(board.getId(),board.getTopic(), board.getTitle(), board.getContent(), board.getAuthor(), board.getCreatedAt());
        } else {
            return null;
        }
    }
}
