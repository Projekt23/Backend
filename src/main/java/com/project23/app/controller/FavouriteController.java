package com.project23.app.controller;

import com.project23.app.Entity.Favourite;
import com.project23.app.dto.DTOFavourite;
import com.project23.app.dto.DTOUser;
import com.project23.app.helper.Mapper;
import com.project23.app.service.FavouriteService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/favourite")
@Log
public class FavouriteController {

    private final Mapper m;
    private final FavouriteService favService;

    @GetMapping(path = "/all/{userId}")
    public List<DTOFavourite> getAllFav(@PathVariable long userId) {
        List<DTOFavourite> favList = new ArrayList<>();
        for(Favourite f : favService.getFavUser(userId)) {
            favList.add(m.favToDtoFav(f));
        }
        return favList;
    }

    @PostMapping(path = "/new")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Created Favourite.")
    public void addFav(@RequestBody DTOFavourite dtoFav){
        favService.addFav(m.dtoFavtoFav(dtoFav));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Favourite.")
    public void deleteFav(@PathVariable long id) {
        favService.deleteFav(id);
    }
}