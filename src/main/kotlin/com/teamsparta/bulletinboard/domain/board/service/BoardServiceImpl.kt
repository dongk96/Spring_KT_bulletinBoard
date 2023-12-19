package com.teamsparta.bulletinboard.domain.board.service

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.board.dto.CreateBoardRequest
import com.teamsparta.bulletinboard.domain.board.dto.UpdateBoardRequest
import com.teamsparta.bulletinboard.domain.board.model.Board
import com.teamsparta.bulletinboard.domain.board.repository.BoardRepository
import com.teamsparta.bulletinboard.domain.exception.ModelNotFoundException
import com.teamsparta.bulletinboard.domain.user.model.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
): BoardService {
    override fun getAllBoardList(): List<BoardResponse> {
        return boardRepository.findAll().map { it.toResponse() }
    }

    override fun getBoard(boardId: Long): BoardResponse {
        val board = boardRepository.findBoardByIdOrIdNull(boardId) ?: throw ModelNotFoundException("board", boardId)
        return board.toResponse()
    }

    @Transactional
    override fun createBoard(request: CreateBoardRequest, username: String): BoardResponse {
        return boardRepository.save(
            Board(
                title = request.title,
                description = request.description,
                date = LocalDate.now(),
                username = username
            )
        ).toResponse()
    }

    @Transactional
    override fun updateBoard(boardId: Long, request: UpdateBoardRequest): BoardResponse {
        val board = boardRepository.findBoardByIdOrIdNull(boardId) ?: throw ModelNotFoundException("board", boardId)
        val (title, description) = request

        board.title = title
        board.description = description

        return boardRepository.save(board).toResponse()
    }

    @Transactional
    override fun removeBoard(boardId: Long) {
        val board = boardRepository.findBoardByIdOrIdNull(boardId) ?: throw ModelNotFoundException("board", boardId)

        boardRepository.delete(board)
    }
}