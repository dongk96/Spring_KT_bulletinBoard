package com.teamsparta.bulletinboard.domain.board.dto

import java.time.LocalDate

data class BoardResponse (
    val id: Long,
    val title: String,
    val username: String,
    val description: String?,
    val date: LocalDate
)
