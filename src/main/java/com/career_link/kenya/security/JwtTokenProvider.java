package com.career_link.kenya.security;

import com.career_link.kenya.utils.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

    /**
     * <a href="https://allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx">...</a>
     * note that  for  this custom method to interpret the jwt token , then it must know of th sign in ke that was   used in signing
     * the token , this  key must remain secret to the app alone .
     *
     * @param JwtToken token to be parsed, that is , interpreted and the body returned.
     *                 {
     *                 "sub": "1234567890",
     *                 "name": "John Doe",
     *                 "iat": 1516239022
     *                 }
     *                 "sub": "1234567890" represents the subject or the identifier of the user.
     *                 "name": "John Doe" represents the name of the user.
     *                 "iat": 1516239022 represents the time at which the token was issued, in seconds since the Unix epoch.
     */



    /**
     * bote  that in this case we had used the userEmail as  the userName , and teh UserDetails class as the type (interface)
     * claims in this case is a map between string values .the subject can be anything unique .
     * note that the extractClaim() method is reusable , depending on the  function that  we  pass as teh second parameter
     * the  hashmap used here will beused to adda dditional info to the jwt token . at long last , the claims object  will have all the details iof the userDetails object  adn the detsils of the
     *  hashmap .
     */

    public String generateJwtToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.JWT_EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateJwtToken(String token, UserDetails userDetails) {
        final String userName = getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public String getUsernameFromToken(String JwtToken) {

        return extractClaim(JwtToken, Claims::getSubject);

    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String JwtToken, Function<Claims, T> claimsResolver) {
        /**
         * note  that the funtion we are passing to this method here is getExpiration , and then we apply it to our jwt token .
         */
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