package com.project23.app.repository;

import com.project23.app.Entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LabelRepository extends JpaRepository<Label, Long> {

    public boolean existsByName(String name);

    public boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bo_2_label WHERE label_id = ?1", nativeQuery = true)
    public void deleteLabelConnection(Long labelId);

    public Label getLabelByName(String l);
}