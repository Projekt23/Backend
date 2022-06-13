package com.project23.app.service;

import com.project23.app.Entity.Statistic;
import com.project23.app.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service-Klasse für Operationen der Entität Statistic (get, add, delete).
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StatisticService {

    private final StatisticRepository statisticRepository;

    /**
     * Speichert ein Statistik-Objekt in der Datenbank.
     * @param statistic Statistic
     * @return true
     */
    public boolean addStatistic(Statistic statistic) {
        statisticRepository.saveAndFlush(statistic);
        return true;
    }

    /**
     * Gibt eine Änderungshistorie zurück (drei Einträge)
     * @return Liste von Statistik-Objekten
     */
    public List<Statistic> getChangeHistory() {
        return statisticRepository.getTop3AllByActionOrderByTimestampDesc(2).stream().distinct().toList();
    }

    /**
     * Gibt die Statistik-Objekte der zuletzt angesehenen BusinessObjects zurück.
     * @param userId User
     * @return Liste von Statistik-Objekten
     */
    public List<Statistic> getLastSeen(long userId) {
        return statisticRepository.getTop5ByActionAndUser_IdOrderByTimestampDesc(3, userId).stream().distinct().toList();
    }

    /**
     * Löscht alle Statistiken zu einem bestimmten BusinessObject.
     * @param boId BusinessObjectId
     */
    public void deleteStatisticByBo(long boId) {
        statisticRepository.deleteStatisticByBusinessObject_Id(boId);
    }

    /**
     * Löscht alle Statistiken zu einem bestimmten User.
     * @param userId UserId
     */
    public void deleteStatsticByUser (long userId) {
        statisticRepository.deleteStatisticByUser_Id(userId);
    }

}
