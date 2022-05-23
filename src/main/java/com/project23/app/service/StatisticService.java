package com.project23.app.service;

import com.project23.app.Entity.Favourite;
import com.project23.app.Entity.Statistic;
import com.project23.app.repository.FavouriteRepository;
import com.project23.app.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public boolean addStatistic(Statistic statistic) {
        statisticRepository.saveAndFlush(statistic);
        return true;
    }

    public List<Statistic> getChangeHistory() {
        return statisticRepository.getTop3AllByActionOrderByTimestampDesc(2).stream().distinct().toList();
    }

    public List<Statistic> getLastSeen(long userId) {
        return statisticRepository.getTop5ByActionAndUser_IdOrderByTimestampDesc(3, userId).stream().distinct().toList();
    }

    public void deleteStatisticByBo(long boId) {
        statisticRepository.deleteStatisticByBusinessObject_Id(boId);
    }

    public void deleteStatsticByUser (long userId) {
        statisticRepository.deleteStatisticByUser_Id(userId);
    }

}
