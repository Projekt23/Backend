package com.project23.app.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"business_object\"", schema = "public")
public class BusinessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "source_id", nullable = false)
    @ManyToOne
    private SourceSystem sourceSystem;

    //ToDO: Synonym / Homonym Objects
    @ManyToMany
    @JoinTable(name="bo_2_bo", joinColumns = @JoinColumn(name="object_id_1", referencedColumnName = "object_id"),
            inverseJoinColumns = @JoinColumn(name="object_id_2", referencedColumnName = "object_id"))
    private List<BusinessObject> synonyms;

    @ManyToMany
    @JoinTable(name="bo_2_label", joinColumns = @JoinColumn(name="object_id", referencedColumnName = "object_id"),
            inverseJoinColumns = @JoinColumn(name="label_id", referencedColumnName = "label_id"))
    private List<Label> labels;

}