package com.project23.app.pojo;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "\"favourite\"", schema = "public")
public class Favourite {
    @Id
    @Column(name = "favourite_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_id", nullable = false)
    private BusinessObject object;


}