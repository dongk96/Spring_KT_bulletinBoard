package com.teamsparta.bulletinboard.domain.security


import com.teamsparta.bulletinboard.domain.user.model.User
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {

    companion object {
        // Authorization Header로 부터 JWT를 획득하기 위한 정규식
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) {
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    // JWT로 부터 정보 획득
                    val userId = it.payload.subject.toLong()
                    val username = it.payload.get("username", String::class.java)
                    val authorities: Collection<GrantedAuthority> = emptyList()

                    val principal = UserPrincipal(
                        id = userId,
                        username = username,
                        authorities = authorities.ifEmpty { listOf(SimpleGrantedAuthority("ROLE_USER")) }
                    )
                    // Authentication 구현체 생성
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        // request로 부터 요청 상세정보 생성
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )
                    // SecurityContext에 authentication 객체 저장
                    SecurityContextHolder.getContext().authentication = authentication
                }
        }
            // FilterChain 계속 진행
        filterChain.doFilter(request, response)
    }



    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}