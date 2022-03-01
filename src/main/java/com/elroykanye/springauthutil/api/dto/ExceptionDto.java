package com.elroykanye.springauthutil.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private String status;
    private String error;
    private String timestamp;
    private String message;
    private String explanation;
    private String path;
}
