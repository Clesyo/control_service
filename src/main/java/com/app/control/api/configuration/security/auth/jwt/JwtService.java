package com.app.control.api.configuration.security.auth.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.control.api.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${security.jwt.subscription-key}")
	private String signKey;

	public String generateToken(User user) {
		Long expiration = Long.valueOf(this.expiration);
		LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(expiration);
		Instant instant = dateExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);

		return Jwts.builder().setSubject(user.getEmail()).setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, this.signKey).compact();
	}

	private Claims obtainClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(this.signKey).parseClaimsJws(token).getBody();
	}

	public String obtainUserLogin(String token) {
		return obtainClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			Claims claims = obtainClaims(token);
			Date dateHourExpiration = claims.getExpiration();
			LocalDateTime obtainDate = dateHourExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(obtainDate);
		} catch (Exception e) {
			return false;
		}
	}
}
