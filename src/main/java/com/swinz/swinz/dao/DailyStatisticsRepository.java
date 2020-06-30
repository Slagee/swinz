package com.swinz.swinz.dao;

import com.swinz.swinz.model.DailyStatistics;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyStatisticsRepository extends CrudRepository<DailyStatistics, Long> {
    DailyStatistics findDailyStatisticsByRoom_IDAndDate(long id, LocalDate date);

    List<DailyStatistics> findDailyStatisticsByRoom_IDAndDateBetween(long id, LocalDate first, LocalDate second);
}
