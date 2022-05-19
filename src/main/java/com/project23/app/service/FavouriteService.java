package com.project23.app.service;

import com.project23.app.Entity.Favourite;
import com.project23.app.Entity.FavouriteKey;
import com.project23.app.Entity.User;
import com.project23.app.repository.FavouriteRepository;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favRepository;
    private final UserService userService;
    private final BusinessObjectService boService;

    public void addFav(Favourite f){
        favRepository.save(f);
    }

    public List<Favourite> getFavUser(Long userId) {
        return favRepository.getFavouritesByUserId(userId);
    }

    public void deleteFav(Long userId, Long boId) {
        FavouriteKey favKey = new FavouriteKey(
                userId,
                boId
        );
        favRepository.deleteById(favKey);
    }

}
