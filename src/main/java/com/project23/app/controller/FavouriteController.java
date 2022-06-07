package com.project23.app.controller;

import com.project23.app.Entity.Favourite;
import com.project23.app.dto.DTOCreateFavourite;
import com.project23.app.dto.DTOFavourite;
import com.project23.app.helper.Mapper;
import com.project23.app.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller-Klasse für den Datentransfer zwischen der Spring-Web-Applikation und dem Web-Client.
 * Stellt diverse Endpunkte für die Entität Favourite zur Verfügung.
 */

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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Created Favourite.")
    public void addFav(@RequestBody DTOCreateFavourite dtoFav){
        favService.addFav(m.dtoCreateFavToFav(dtoFav));
    }

    @DeleteMapping(path = "/{userId}/{businessObjectId}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Favourite.")
    public void deleteFav(@PathVariable Long userId, @PathVariable Long businessObjectId) {
        favService.deleteFav(userId, businessObjectId);
    }
}