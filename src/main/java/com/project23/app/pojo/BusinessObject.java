package com.project23.app.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"business_object\"", schema = "public")
public class BusinessObject {
    @Id
    @Column(name = "object_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "source_id", nullable = false)
    private Integer sourceId;

}