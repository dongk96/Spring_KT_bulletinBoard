package com.teamsparta.bulletinboard.domain.security

import org.springframework.security.core.GrantedAuthority

data class UserPrincipal(
    val id: Long,
    val username: String,
    val authorities: Collection<GrantedAuthority>
)
