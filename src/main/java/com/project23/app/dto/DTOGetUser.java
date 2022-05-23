package com.project23.app.dto;

import com.project23.app.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class DTOGetUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String eMail;



}
