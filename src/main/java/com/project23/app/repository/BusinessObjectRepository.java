package com.project23.app.repository;

import com.project23.app.Entity.BusinessObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository Interface für den Datentransfer der Entität BusinessObject.
 */

public interface BusinessObjectRepository extends JpaRepository<BusinessObject, Long> {

    public boolean existsByNameAndDescription(String name, String description);
    public boolean existsById(Long id);

    /**
     * Methode zum Abrufen der ID eines zufällig ausgewählten BusinessObjects.
     * @return
     */
    @Transactional
    @Query(value = "SELECT object_id FROM business_object ORDER BY random() LIMIT 1", nativeQuery = true)
    public int getRandomBusinessObjectId();

}