package com.project23.app.pojo;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "\"user\"", schema = "public")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String eMail;

    @Column(name = "password", nullable = false)
    private String password;

}