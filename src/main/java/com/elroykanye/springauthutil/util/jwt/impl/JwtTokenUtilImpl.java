package com.elroykanye.springauthutil.util.jwt.impl;

import com.elroykanye.springauthutil.util.jwt.JwtTokenUtil;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

/**
 * @author Elroy
 * @date 24 - 02 - 2022
 * @since 1.0
 */
@Slf4j
@Component
@Getter @Setter
public class JwtTokenUtilImpl implements Serializable, JwtTokenUtil {
    //private static final long serialVersionUID = -2550185165626007488L;

    private static long tokenValidity = 3600000; // 1h

    private static SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;


    private static String secret = "mySecret";

    private static String tokenPrefix = "Bearer ";

    private static String headerString = "Authorization";

    public JwtTokenUtilImpl() {
        tokenValidity = 3600;
        secret = "mySecret";
        algorithm = SignatureAlgorithm.HS512;
        tokenPrefix = "Bearer";
        headerString = "Authorization";

    }

    public JwtTokenUtilImpl(long jwtTokenValidity){
        tokenValidity = jwtTokenValidity;
    }

    public JwtTokenUtilImpl(long jwtTokenValidity, SignatureAlgorithm signatureAlgorithm) {
        tokenValidity = jwtTokenValidity;
        algorithm = signatureAlgorithm;
    }

    public JwtTokenUtilImpl(long jwtTokenValidity, SignatureAlgorithm signatureAlgorithm, String jwtSecret) {
        tokenValidity = jwtTokenValidity;
        algorithm = signatureAlgorithm;
        secret = jwtSecret;
    }

    public JwtTokenUtilImpl(long jwtTokenValidity, SignatureAlgorithm signatureAlgorithm, String jwtSecret, String jwtTokenPrefix) {
        tokenValidity = jwtTokenValidity;
        algorithm = signatureAlgorithm;
        secret = jwtSecret;
        tokenPrefix = jwtTokenPrefix;
    }

    public JwtTokenUtilImpl(long jwtTokenValidity, SignatureAlgorithm signatureAlgorithm, String jwtSecret, String jwtTokenPrefix, String jwtHeaderString) {
        tokenValidity = jwtTokenValidity;
        algorithm = signatureAlgorithm;
        secret = jwtSecret;
        tokenPrefix = jwtTokenPrefix;
        headerString = jwtHeaderString;
    }


    /**
     * Get username from token
     * @param jwtToken JWT token
     * @return username
     */
    @Override
    public String getUsernameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    /**
     * Check if the token has expired
     * @param jwtToken JWT token to be checked
     * @return true if the token has expired, false otherwise
     */
    public static Date getExpirationDateFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }

    /**
     * Get claim from a token
     * @param jwtToken JWT token
     * @param claimsResolver function to resolve the claim
     * @param <K> return type
     * @return the claim
     */
    public static <K> K getClaimFromToken(String jwtToken, Function<Claims, K> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }

    /**
     * Get all claims from a token
     * @param token JWT token
     * @return all claims
     */
    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generate a JWT token
     * @param username username to be added to the token
     * @return JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (tokenValidity * 1000L)))
                .compact();
    }

    /**
     * Generate a JWT token
     * @param jwtTokenInfo JWT token info
     * @return JWT token
     */
    @Override
    public String generateToken(JwtTokenInfo jwtTokenInfo) {
        jwtTokenInfo.setExpirationDate(new Date(System.currentTimeMillis() + (tokenValidity * 1000L)));

        return Jwts.builder()
                .setExpiration(jwtTokenInfo.getExpirationDate())
                .setIssuer(jwtTokenInfo.getIssuer())
                .setAudience(jwtTokenInfo.getAudience())
                .setSubject(jwtTokenInfo.getSubject())
                .setId(jwtTokenInfo.getJwtId())
                .setIssuedAt(new Date())
                .setClaims(jwtTokenInfo.getClaims())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * Validate token
     * @param token JWT token
     * @return true if the token is valid, false otherwise
     */
    /*
    private Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    */
    /**
     * @param token JWT token
     * @param username username to be checked
     * @return true if the token is valid, false otherwise
     */
    private Boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Check if the token is expired
     * @param token JWT token
     * @return true if the token is expired, false otherwise
     */
    public static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}