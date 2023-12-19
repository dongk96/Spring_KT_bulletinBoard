package com.teamsparta.bulletinboard.domain.board.dto

import java.time.LocalDate

data class CreateBoardRequest (
    val title: String,
    val description: String
)
