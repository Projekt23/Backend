package com.project23.app.repository;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, Long> {

    public boolean existsByName(String name);
}