package com.project23.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //ToDO: Synonym / Homonym Objects
    @ManyToMany
    @JoinTable(name="bo_2_synonym", joinColumns = @JoinColumn(name="object_id", referencedColumnName = "object_id"),
            inverseJoinColumns = @JoinColumn(name="synonym", referencedColumnName = "object_id"))
    private List<BusinessObject> synonyms;

    @ManyToMany
    @JoinTable(name="bo_2_label", joinColumns = @JoinColumn(name="object_id", referencedColumnName = "object_id"),
            inverseJoinColumns = @JoinColumn(name="label_id", referencedColumnName = "label_id"))
    private List<Label> labels;

    @ManyToMany
    @JoinTable(name="bo_context", joinColumns = @JoinColumn(name="object_id_1", referencedColumnName = "object_id"),
            inverseJoinColumns = @JoinColumn(name="object_id_2", referencedColumnName = "object_id"))
    private List<BusinessObject> context;

    public BusinessObject(String name, String description, List<BusinessObject> synonyms, List<Label> labels, List<BusinessObject> contextList) {
        this.name = name;
        this.description = description;
        this.synonyms = synonyms;
        this.labels = labels;
        this.context = contextList;
    }
}