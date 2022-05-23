package com.project23.app.repository;

import com.project23.app.Entity.Statistic;
import com.project23.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    public List<Statistic> getTop3AllByActionOrderByTimestampDesc(int action);
    public List<Statistic> getTop5ByActionAndUser_IdOrderByTimestampDesc(int action, long userId);
    public void deleteStatisticByBusinessObject_Id(long boId);
}