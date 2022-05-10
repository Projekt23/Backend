package com.project23.app.repository;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    public boolean existsByName(String name);

}