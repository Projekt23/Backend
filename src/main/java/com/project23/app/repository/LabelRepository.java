package com.project23.app.repository;

import com.project23.app.Entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

    public boolean existsByName(String name);

}