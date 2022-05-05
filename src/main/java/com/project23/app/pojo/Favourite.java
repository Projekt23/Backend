package com.project23.app.pojo;

import javax.persistence.*;

@Entity
@Table(name = "\"Favourite\"")
public class Favourite {
    @Id
    @Column(name = "favourite_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_id", nullable = false)
    private BusinessObject object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BusinessObject getObject() {
        return object;
    }

    public void setObject(BusinessObject object) {
        this.object = object;
    }

}