package com.agallas.agallasback.security.jwt;

import com.agallas.agallasback.security.details.UserCustomDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
    Generación y validación del token
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expiration;

    public String generateToken(Authentication authentication){
        UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            log.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            log.error("Token expirado");
        } catch (IllegalArgumentException e) {
            log.error("Token vacio");
        } catch (SignatureException e) {
            log.error("Fallo con la firma");
        }
        return false;
    }

}
