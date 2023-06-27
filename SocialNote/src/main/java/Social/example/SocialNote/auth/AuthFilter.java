package Social.example.SocialNote.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.exceptions.UnauthorizedException;
import Social.example.SocialNote.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

@Log
@Component
public class AuthFilter extends OncePerRequestFilter{
	
	@Autowired
	UserService uService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String headerAuth = request.getHeader("Authorization");
		
		if (headerAuth == null | !headerAuth.startsWith("Bearer ")) {
			throw new UnauthorizedException("add a valid token");
		};
		
		String accesToken = headerAuth.substring(8);
		
		JWTools.isTokenValid(accesToken);
		
		String username = JWTools.extractSubject(accesToken);
		log.info("-------->   " + username);
		
		try {
			
			User u = uService.findByUsername(username);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			filterChain.doFilter(request, response);
			
		} catch (NotFoundException e) {
			log.info("error ------->    " + e);
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("authorization/++++++++++", request.getServletPath());
	};
}
