package com.career_link.kenya.security;

import com.career_link.kenya.utils.ApplicationConstants;
import com.career_link.kenya.utils.LoggingTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

    public String generateJwtToken(Map<String, String> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.JWT_EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateJwtToken(String token, UserDetails userDetails) {

        try {
            System.out.println(userDetails);
            final String userName = getUsernameFromToken(token);
            System.out.println(userName);
            return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String getUsernameFromToken(String JwtToken) {
        try {
            return extractClaim(JwtToken, Claims::getSubject);

        } catch (Exception e) {
            ApplicationConstants.LOG(e.getMessage(), LoggingTypes.ERROR);
            throw new RuntimeException(e.getMessage());
        }


    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String JwtToken, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(JwtToken);
        return claimsResolver.apply(claims);


    }

    private Claims extractAllClaims(String JwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(JwtToken).getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ApplicationConstants.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}