package com.project23.app.repository;

import com.project23.app.Entity.Favourite;
import com.project23.app.Entity.FavouriteKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository Interface für den Datentransfer der Entität Favourite.
 */

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteKey> {

    public List<Favourite> getFavouritesByUserId(Long userId);
    public void deleteFavouritesByBusinessObjectId(Long boId);
    public void deleteFavouritesByUserId(Long userId);

}