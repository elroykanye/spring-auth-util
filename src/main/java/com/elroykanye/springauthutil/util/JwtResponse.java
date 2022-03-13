package com.elroykanye.springauthutil.util;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private Long id;
    private String jwtToken;
    private String username;
    private String jwtTokenType;
    private List<String> roles;
}
