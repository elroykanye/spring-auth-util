package com.elroykanye.springauthutil.util.jwt.impl;

import lombok.*;

import java.util.Date;
import java.util.Map;

/**
 * @author Elroy Kanye
 * @date 24 - 02 - 2022
 * @since 1.0
 * @version 1.0
 * @description JwtTokenInfo used to store the token information to be used in the generation of the token
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenInfo {
    private String username;
    private Date expirationDate;
    private String role;
    private String issuer;
    private String audience;
    private String subject;
    private String jwtId;
    Map<String, Object> claims;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}
