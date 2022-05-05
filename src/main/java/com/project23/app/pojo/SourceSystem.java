package com.project23.app.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Source_System\"")
public class SourceSystem {
    @Id
    @Column(name = "source_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private Integer name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

}