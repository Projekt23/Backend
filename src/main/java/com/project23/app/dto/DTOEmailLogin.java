package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOEmailLogin {
    private String eMail;
    private String password;
}