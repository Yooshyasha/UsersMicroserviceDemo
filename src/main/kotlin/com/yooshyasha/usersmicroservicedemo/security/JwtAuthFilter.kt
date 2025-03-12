package com.yooshyasha.usersmicroservicedemo.security

import com.yooshyasha.usersmicroservicedemo.repos.UserRepo
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtUtil: JwtUtil,
    private val userRepo: UserRepo,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: jakarta.servlet.http.HttpServletRequest,
        response: jakarta.servlet.http.HttpServletResponse,
        filterChain: jakarta.servlet.FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return filterChain.doFilter(request, response)
        }

        val token = authHeader.substring(7)

        try {
            val username = jwtUtil.extractUsernameFromToken(token)
                ?: return filterChain.doFilter(request, response)

            val user = userRepo.findByUserName(username)
                ?: return filterChain.doFilter(request, response)

            if (jwtUtil.verifyToken(token, user)) {
                val auth = UsernamePasswordAuthenticationToken(user, null, user.authorities)

                auth.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e: Exception) {
            logger.error(e.message)
        }

        return filterChain.doFilter(request, response)
    }
}