package Social.example.SocialNote.auth;

import org.springframework.stereotype.Component;

import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Component
public class JWTools {
	
	private static String secret;
	private static int expiration;
	
	@Value("${spring.application.jwt.secret}")
	public void setSecret(String keySecret) {
		secret = keySecret;
	};
	
	@Value("${spring.appliaction.jwt.expirationindays}")
	public void setExpiration(String expirationDays) {
		expiration = Integer.parseInt(expirationDays) * 1000 * 60 * 60 * 24 * 14;
	};

	public static String createToken(User us) {
		String token = Jwts.builder().setSubject(us.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		
		return token;
	};
	
	public static void isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
			
		} catch (MalformedJwtException e) {
			
			throw new UnauthorizedException("not valid token");
			
		}catch(ExpiredJwtException e) {
			
			throw new UnauthorizedException("expired token");
			
		} catch (Exception e) {
			
			throw new UnauthorizedException("error, please try login again");
		};
	};
	
	public static String  extractSubject(String token) {
		
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
	};
}
