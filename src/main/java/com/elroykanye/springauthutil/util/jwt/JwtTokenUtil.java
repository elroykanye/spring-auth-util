package com.elroykanye.springauthutil.util.jwt;

import com.elroykanye.springauthutil.util.jwt.impl.JwtTokenInfo;

public interface JwtTokenUtil {
    String getUsernameFromToken(String token);

    String generateToken(String username);

    String generateToken(JwtTokenInfo jwtTokenInfo);

}
