package com.teamsparta.bulletinboard.domain.user.model

import com.teamsparta.bulletinboard.domain.board.model.Board
import com.teamsparta.bulletinboard.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "board_user")
class User (
    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String

//    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
//    val boards: MutableList<Board> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toResponse(): UserResponse {
        return UserResponse(
            id = id!!,
            username = username
        )
    }
}