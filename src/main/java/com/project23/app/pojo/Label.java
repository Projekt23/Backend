package com.project23.app.pojo;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "\"label\"", schema = "public")
public class Label {
    @Id
    @Column(name = "label_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


}