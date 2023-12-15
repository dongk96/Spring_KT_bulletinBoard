package com.teamsparta.bulletinboard.domain.user.model

import com.teamsparta.bulletinboard.domain.board.model.Board
import jakarta.persistence.*

@Entity
@Table(name = "board_user")
class User (
    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val boards: MutableList<Board> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}