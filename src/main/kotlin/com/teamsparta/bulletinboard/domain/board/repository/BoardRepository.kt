package com.teamsparta.bulletinboard.domain.board.repository

import com.teamsparta.bulletinboard.domain.board.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
    fun findALl(): List<Board>

    fun findBoardById(boardId: Long): Board

}