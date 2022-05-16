package com.project23.app.service;

import com.project23.app.Entity.Favourite;
import com.project23.app.repository.FavouriteRepository;
import com.project23.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteService {

    private final FavouriteRepository favRepository;

    public void addFav(Favourite f){
        favRepository.save(f);
    }

    public List<Favourite> getFavUser(Long userId) {
        return favRepository.getFavouritesByUser_Id(userId);
    }

    public void deleteFav(Long id) {
        favRepository.deleteById(id);
    }

}
