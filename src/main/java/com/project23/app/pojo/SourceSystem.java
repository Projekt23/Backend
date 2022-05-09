package com.project23.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"source_system\"", schema = "public")
public class SourceSystem {
    @Id
    @Column(name = "source_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


}