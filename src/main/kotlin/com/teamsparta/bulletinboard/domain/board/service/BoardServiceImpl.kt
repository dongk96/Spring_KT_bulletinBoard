package com.teamsparta.bulletinboard.domain.board.service

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.board.dto.CreateBoardRequest
import com.teamsparta.bulletinboard.domain.board.dto.UpdateBoardRequest
import com.teamsparta.bulletinboard.domain.board.repository.BoardRepository
import com.teamsparta.bulletinboard.domain.exception.ModelNotFoundException
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
): BoardService {
    override fun getAllBoardList(): List<BoardResponse> {
        return boardRepository.findALl().map { it.toResponse() }
    }

    override fun getBoard(boardId: Long): BoardResponse {
        val board = boardRepository.findBoardById(boardId) ?: throw ModelNotFoundException("board", boardId)
        return board.toResponse()
    }

    override fun createBoard(request: CreateBoardRequest): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun updateBoard(boardId: Long, request: UpdateBoardRequest): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun removeBoard(boardId: Long) {
        TODO("Not yet implemented")
    }
}