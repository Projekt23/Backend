package com.project23.app.repository;

import com.project23.app.Entity.BusinessObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, Long> {

    public boolean existsByName(String name);
}