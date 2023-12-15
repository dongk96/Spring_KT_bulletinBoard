package com.teamsparta.bulletinboard.domain.board.service

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.board.dto.CreateBoardRequest
import com.teamsparta.bulletinboard.domain.board.dto.UpdateBoardRequest

interface BoardService {
    fun getAllBoardList(): List<BoardResponse>

    fun getBoard(boardId: Long): BoardResponse

    fun createBoard(request: CreateBoardRequest): BoardResponse

    fun updateBoard(boardId: Long, request: UpdateBoardRequest): BoardResponse

    fun removeBoard(boardId: Long)
}