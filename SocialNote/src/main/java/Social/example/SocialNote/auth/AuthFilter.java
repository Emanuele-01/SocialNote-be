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
		
		if(!request.getMethod().equals("OPTIONS")) {
			
			String headerAuth = request.getHeader("Authorization");
			
			
			System.out.println(headerAuth);
			if (headerAuth == null || !headerAuth.startsWith("Bearer ")) {
				throw new UnauthorizedException("add a valid token");
			};
			
			String accessToken = headerAuth.substring(7);
			
			JWTTools.isTokenValid(accessToken);
			
			String email = JWTTools.extractSubject(accessToken);
			log.info("-------->   " + email);
			
			try {
				
				User u = uService.findByEmail(email);
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
				filterChain.doFilter(request, response);
				
			} catch (NotFoundException e) {
				log.info("error ------->    " + e);
			}
		}else {
			
			filterChain.doFilter(request, response);
		}
		
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		
		boolean shouldFilter = new AntPathMatcher().match("/auth/**", servletPath);
		
		shouldFilter = shouldFilter || new AntPathMatcher().match("/social&note/post", servletPath);
		
		shouldFilter = shouldFilter || new AntPathMatcher().match("/social&note/users", servletPath);
		
		return shouldFilter;
	};
}
