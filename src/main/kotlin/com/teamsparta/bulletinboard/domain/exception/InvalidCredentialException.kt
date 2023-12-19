package com.teamsparta.bulletinboard.domain.exception

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()