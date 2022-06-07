package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JPA-Entity User zum Datentransfer zwischen der Spring-Web-Applikation und der Datenbank.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", unique=true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique=true, nullable = false)
    private String username;

    @Column(name = "email", unique=true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}