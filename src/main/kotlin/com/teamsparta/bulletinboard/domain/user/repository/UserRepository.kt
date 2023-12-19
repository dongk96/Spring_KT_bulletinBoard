package com.teamsparta.bulletinboard.domain.user.repository

import com.teamsparta.bulletinboard.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): User?
}