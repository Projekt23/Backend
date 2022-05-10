package com.project23.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"label\"", schema = "public")
public class Label {
    @Id
    @Column(name = "label_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}