package com.teamsparta.bulletinboard.domain.board.repository

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.board.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: JpaRepository<Board, Long> {

    fun findBoardByIdOrIdNull(boardId: Long): Board?

}