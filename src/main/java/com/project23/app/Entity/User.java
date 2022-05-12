package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique=true, nullable = false)
    private String username;

    @Column(name = "email", unique=true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}