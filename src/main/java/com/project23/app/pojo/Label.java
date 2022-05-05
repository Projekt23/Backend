package com.project23.app.pojo;

import javax.persistence.*;

@Entity
@Table(name = "\"Label\"")
public class Label {
    @Id
    @Column(name = "label_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}