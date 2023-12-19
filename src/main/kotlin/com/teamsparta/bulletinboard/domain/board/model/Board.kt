package com.teamsparta.bulletinboard.domain.board.model

import com.teamsparta.bulletinboard.domain.board.dto.BoardResponse
import com.teamsparta.bulletinboard.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "board")
class Board (
    @Column(name = "title", nullable = false)
    var title: String,

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    var user: User,
    @Column(name = "username", nullable = false)
    val username:String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "date")
    val date: LocalDate
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toResponse(): BoardResponse {
        return BoardResponse(
            id = id!!,
            title = title,
            username = username,
            description = description,
            date = date
        )
    }
}