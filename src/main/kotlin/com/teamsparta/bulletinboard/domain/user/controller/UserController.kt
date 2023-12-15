package com.teamsparta.bulletinboard.domain.user.controller

import com.teamsparta.bulletinboard.domain.user.dto.LogInRequest
import com.teamsparta.bulletinboard.domain.user.dto.SignUpRequest
import com.teamsparta.bulletinboard.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        TODO()
    }

    @PostMapping("/lognin")
    fun logIn(@RequestBody logInRequest: LogInRequest): ResponseEntity<Unit> {
        TODO()
    }
}