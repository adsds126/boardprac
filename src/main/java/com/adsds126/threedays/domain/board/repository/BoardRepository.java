package com.adsds126.threedays.domain.board.repository;

import com.adsds126.threedays.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
