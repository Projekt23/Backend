package com.project23.app.repository;

import com.project23.app.Entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository Interface für den Datentransfer der Entität Statistic.
 */

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    public List<Statistic> getTop3AllByActionOrderByTimestampDesc(int action);
    public List<Statistic> getTop5ByActionAndUser_IdOrderByTimestampDesc(int action, long userId);
    public void deleteStatisticByBusinessObject_Id(long boId);
    public void deleteStatisticByUser_Id(long userId);
}