package com.teamsparta.bulletinboard.domain.user.service

import com.teamsparta.bulletinboard.domain.exception.ModelNotFoundException
import com.teamsparta.bulletinboard.domain.security.JwtPlugin
import com.teamsparta.bulletinboard.domain.user.dto.LogInRequest
import com.teamsparta.bulletinboard.domain.user.dto.LoginResponse
import com.teamsparta.bulletinboard.domain.user.dto.SignUpRequest
import com.teamsparta.bulletinboard.domain.user.dto.UserResponse
import com.teamsparta.bulletinboard.domain.user.model.User
import com.teamsparta.bulletinboard.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if(userRepository.existsByUsername(signUpRequest.username)) {
            throw IllegalStateException("Username is already in use")
        }

        return userRepository.save(
            User(
                username = signUpRequest.username,
                password = passwordEncoder.encode(signUpRequest.password)
            )
        ).toResponse()
    }

    override fun logIn(logInRequest: LogInRequest): LoginResponse {
        val user = userRepository.findByUsername(logInRequest.username) ?: throw ModelNotFoundException("User", null)

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                username = user.username
            )
        )
    }

}