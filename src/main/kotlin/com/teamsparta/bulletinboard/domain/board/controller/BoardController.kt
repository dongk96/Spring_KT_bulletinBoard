package com.teamsparta.bulletinboard.domain.board.controller

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.board.dto.CreateBoardRequest
import com.teamsparta.bulletinboard.domain.board.dto.UpdateBoardRequest
import com.teamsparta.bulletinboard.domain.board.service.BoardService
import com.teamsparta.bulletinboard.domain.exception.ModelNotFoundException
import com.teamsparta.bulletinboard.domain.security.UserPrincipal
import com.teamsparta.bulletinboard.domain.user.model.User
import com.teamsparta.bulletinboard.domain.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/boards")
@RestController
class BoardController (
    private val boardService: BoardService
) {

    @GetMapping
    fun getBoardList(): ResponseEntity<List<BoardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getAllBoardList())
    }

    @GetMapping("/{boardId}")
    fun getBoard(@PathVariable boardId: Long): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getBoard(boardId))
    }

    @PostMapping
    fun createBoard(@RequestBody createBoardRequest: CreateBoardRequest, @AuthenticationPrincipal principal: User):ResponseEntity<BoardResponse> {
        val username = principal.username

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.createBoard(createBoardRequest, username))
    }

    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable boardId: Long,
        @RequestBody updateBoardRequest: UpdateBoardRequest,
        @AuthenticationPrincipal user: User
        ): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.updateBoard(boardId, updateBoardRequest))
    }

    @DeleteMapping("/{boardId}")
    fun removeBoard(@PathVariable boardId: Long): ResponseEntity<Unit> {
        boardService.removeBoard(boardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}