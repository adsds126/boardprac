package com.adsds126.threedays.domain.board.controller;

import com.adsds126.threedays.domain.board.dto.BoardDto;
import com.adsds126.threedays.domain.board.entity.Board;
import com.adsds126.threedays.domain.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> postBoard(BoardDto.PostDto postDto, @AuthenticationPrincipal UserDetails userDetails) {
        Board newBoard = boardService.post(postDto, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable("board-id") Long boardId, @AuthenticationPrincipal UserDetails userDetails){
        BoardDto.Response response = boardService.getBoard(boardId, userDetails);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
