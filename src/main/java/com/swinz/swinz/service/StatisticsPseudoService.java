package com.swinz.swinz.service;

import com.swinz.swinz.model.DailyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatisticsPseudoService {

    private final DailyStatisticsService dailyStatisticsService;

    @Autowired
    public StatisticsPseudoService(DailyStatisticsService dailyStatisticsService) {
        this.dailyStatisticsService = dailyStatisticsService;
    }

    public double getAverageLightStateForLastWeekByRoomID(long id) {
        double sumInSeconds = 0;
        for (DailyStatistics dailyStatistics : dailyStatisticsService.getDailyStatisticsByRoomIDAndDateBetween(id, LocalDate.now().minusDays(7), LocalDate.now())) {
            sumInSeconds += dailyStatistics.getLightOnTimeInSeconds();
        }
        double sumInHours = sumInSeconds / 3600.0;
        return sumInHours / 7.0;
    }
}
