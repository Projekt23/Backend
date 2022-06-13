package com.project23.app.service;

import com.project23.app.Entity.Favourite;
import com.project23.app.Entity.FavouriteKey;
import com.project23.app.Entity.User;
import com.project23.app.repository.FavouriteRepository;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service-Klasse für Operationen der Entität Favourite (get, add, delete).
 */

@Service
@Transactional
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favRepository;

    /**
     * Speichert ein BusinessObject als Favorit für den angemeldeten User.
     * @param f Favourite
     */
    public void addFav(Favourite f){
        favRepository.save(f);
    }

    /**
     * Gibt Favoriten eines Users zurück
     * @param userId User, dessen Favoriten zurückggeben werden
     * @return Liste von Favoriten
     */
    public List<Favourite> getFavUser(Long userId) {
        return favRepository.getFavouritesByUserId(userId);
    }

    /**
     * Löscht ein BusinessObject aus den Favoriten.
     * @param userId User, dessen Favoriten bearbeitet werden
     * @param boId ID des BusinessObjects, das aus den Favoriten gelöscht wird
     */
    public void deleteFav(Long userId, Long boId) {
        FavouriteKey favKey = new FavouriteKey(
                userId,
                boId
        );
        favRepository.deleteById(favKey);
    }

    /**
     * Löscht alle Favoriten eines Users.
     * @param userId User, dessen Favoriten gelöscht werden
     */
    public void deleteFavByUser(Long userId) {
        favRepository.deleteFavouritesByUserId(userId);
    }

    /**
     * Löscht alle Favoriten, die in Relation zu einem bestimmten BusinessObject stehen.
     * @param boId BusinessObject, das aus allen Favoriten entfernt wird
     */
    public void deleteFavByBo(Long boId) {
        favRepository.deleteFavouritesByBusinessObjectId(boId);
    }

}
