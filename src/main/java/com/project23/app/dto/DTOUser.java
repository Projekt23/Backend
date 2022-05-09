package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DTOUser {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String eMail;

    private String password;
}
