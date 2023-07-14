package com.zimttech.diabeticscreener.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyJwtTokenRequest {
    private String token;
}
