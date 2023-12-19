package com.teamsparta.bulletinboard.domain.user.controller

import com.teamsparta.bulletinboard.domain.user.dto.LogInRequest
import com.teamsparta.bulletinboard.domain.user.dto.LoginResponse
import com.teamsparta.bulletinboard.domain.user.dto.SignUpRequest
import com.teamsparta.bulletinboard.domain.user.dto.UserResponse
import com.teamsparta.bulletinboard.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun logIn(@RequestBody logInRequest: LogInRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.logIn(logInRequest))
    }
}