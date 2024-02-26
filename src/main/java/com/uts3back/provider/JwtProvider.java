package com.uts3back.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    private String secretKey = "S3cr3tk3y";

    public String create(String email){

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); //현재 시간부터 한시간 후

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();


        return jwt;
    }

    public String validate(String jwt) {

        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();

        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }

}
