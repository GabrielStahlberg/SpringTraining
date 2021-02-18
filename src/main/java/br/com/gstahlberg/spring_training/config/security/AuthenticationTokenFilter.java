package br.com.gstahlberg.spring_training.config.security;

import br.com.gstahlberg.spring_training.model.User;
import br.com.gstahlberg.spring_training.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    public AuthenticationTokenFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = retrieveToken(request);
        boolean isTokenValid = tokenService.isTokenValid(token);
        if(isTokenValid) {
            doUserAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private void doUserAuthentication(String token) {
        Long userId = tokenService.getUserId(token);
        Optional<User> userOptional = userService.findUserById(userId);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
