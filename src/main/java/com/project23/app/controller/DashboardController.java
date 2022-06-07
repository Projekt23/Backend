package com.project23.app.controller;

import com.project23.app.dto.DTODashboard;
import com.project23.app.helper.Mapper;
import com.project23.app.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

/**
 * Controller-Klasse für den Datentransfer zwischen der Spring-Web-Applikation und dem Web-Client.
 * Stellt einen Endpunkt für das Dashboard (Startseite) zur Verfügung.
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/dashboard")
@Log
public class DashboardController {

    private final Mapper m;
    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<DTODashboard> getDashboard(@RequestParam Long userId) {
        DTODashboard dashboard = new DTODashboard(
                m.statisticsToDtoChangeHistory(statisticService.getChangeHistory()),
                m.statisticsToDtoLastSeen(statisticService.getLastSeen(userId)),
                m.boToDtoRandom()
        );
        return ResponseEntity.ok().body(dashboard);
    }

}