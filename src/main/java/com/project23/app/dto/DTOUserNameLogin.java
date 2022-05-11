package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DTOUserNameLogin {
    private String username;
    private String password;
}