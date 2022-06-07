package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO-Klasse zum Datentransfer zwischen Spring-Web-Applikation und Web-Client entsprechend der API-Spezifikation.
 */

@Data
@AllArgsConstructor
public class DTOUserNameLogin {
    private String username;
    private String password;
}