package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"label\"", schema = "public")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Label(String name) {
        this.name = name;
    }
}