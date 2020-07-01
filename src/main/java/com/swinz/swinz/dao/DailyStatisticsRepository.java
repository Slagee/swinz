package com.swinz.swinz.dao;

import com.swinz.swinz.model.DailyStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyStatisticsRepository extends CrudRepository<DailyStatistics, Long> {
    DailyStatistics findDailyStatisticsByRoom_IDAndDate(long id, LocalDate date);

    List<DailyStatistics> findDailyStatisticsByRoom_IDAndDateBetween(long id, LocalDate first, LocalDate second);

    List<DailyStatistics> findDailyStatisticsByRoom_IDAndMonth(long id, int month);
}
