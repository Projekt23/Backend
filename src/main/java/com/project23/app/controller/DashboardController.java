package com.project23.app.controller;

import com.project23.app.Entity.Favourite;
import com.project23.app.dto.DTODashboard;
import com.project23.app.dto.DTOFavourite;
import com.project23.app.helper.Mapper;
import com.project23.app.service.FavouriteService;
import com.project23.app.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/dashboard")
@Log
public class DashboardController {

    private final Mapper m;
    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<DTODashboard> getDashboard() {
        DTODashboard dashboard = new DTODashboard(
                m.statisticsToDtoChangeHistory(statisticService.getChangeHistory()),
                m.statisticsToDtoLastSeen(statisticService.getLastSeen(1)),
                m.boToDtoRandom()
        );
        return ResponseEntity.ok().body(dashboard);
    }

}