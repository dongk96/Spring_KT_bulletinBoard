package com.teamsparta.bulletinboard.domain.user.dto

data class SignUpRequest (
    val username: String,

    val password: String
) {
    companion object {
        private const val USERNAME_REGEX = "^[a-z0-9]{4,10}$"
        private const val PASSWORD_REGEX = "^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{4,10}$"
    }

    init {
        require(username.matches(Regex(USERNAME_REGEX))) {
            "Username must be 4 to 10 characters long and consist of lowercase letters and numbers."
        }

        require(password.matches(Regex(PASSWORD_REGEX))) {
            "Password must be 4 to 10 characters long and consist of alphanumeric characters and special characters."
        }
    }
}
