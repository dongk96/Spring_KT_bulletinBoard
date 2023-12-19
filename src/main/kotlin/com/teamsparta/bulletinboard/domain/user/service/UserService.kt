package com.teamsparta.bulletinboard.domain.user.service

import com.teamsparta.bulletinboard.domain.user.dto.LogInRequest
import com.teamsparta.bulletinboard.domain.user.dto.LoginResponse
import com.teamsparta.bulletinboard.domain.user.dto.SignUpRequest
import com.teamsparta.bulletinboard.domain.user.dto.UserResponse

interface UserService {
    fun signUp(signUpRequest: SignUpRequest): UserResponse

    fun logIn(logInRequest: LogInRequest): LoginResponse
}
