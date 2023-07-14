package com.zimttech.diabeticscreener.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgotPasswordLinkRequest {
    private String recipient;
    private String link;

}
