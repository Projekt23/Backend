package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class RegistrationToken implements Serializable {
    private String email;
    private String exp;
}
