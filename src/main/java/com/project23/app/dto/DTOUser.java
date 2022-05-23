package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DTOUser {

    private Long id;

    private String lastName;

    private String firstName;

    private String username;

    private String eMail;

    private String password;

}
