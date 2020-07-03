package com.swinz.swinz.statistics;

import com.swinz.swinz.model.DailyStatistics;
import com.swinz.swinz.service.DailyStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component
public class StatisticsUtil {

    private final DailyStatisticsService dailyStatisticsService;

    @Autowired
    public StatisticsUtil(DailyStatisticsService dailyStatisticsService) {
        this.dailyStatisticsService = dailyStatisticsService;
    }

    public double getAverageLightStateForLastWeekByRoomID(long id) {
        List<DailyStatistics> dailyStatistics =
                dailyStatisticsService.getDailyStatisticsByRoomIDAndDateBetween(id, LocalDate.now().minusDays(7), LocalDate.now());
        return getLightStateInHours(dailyStatistics) / 7.0;
    }

    public int getNumberOfDaysWhenRadiatorWasOn() {
        return getNumberOfDaysWhenRadiatorWasOn(dailyStatisticsService.getAllDailyStatistics());
    }

    public double getAverageLightStateByRoomIDAndMonthValue(long id, int monthValue) {
        List<DailyStatistics> dailyStatistics = dailyStatisticsService.getDailyStatisticsByRoomIDAndMonthValue(id, monthValue);
        return getLightStateInHours(dailyStatistics) / (YearMonth.of(LocalDate.now().getYear(), monthValue).lengthOfMonth());
    }

    public int getMonthlyPowerConsumptionByRoomIDAndMonthValue(long id, int monthValue) {
        int summedPowerConsumption = 0;
        for (DailyStatistics dailyStatistics : dailyStatisticsService.getDailyStatisticsByRoomIDAndMonthValue(id, monthValue)) {
            summedPowerConsumption += dailyStatistics.getPowerConsumption();
        }
        return summedPowerConsumption;
    }

    public int getNumberOfDaysInMonthWhenRadiatorWasOn(long roomID, int monthValue) {
        return getNumberOfDaysWhenRadiatorWasOn(dailyStatisticsService.getDailyStatisticsByRoomIDAndMonthValue(roomID, monthValue));
    }

    private double getLightStateInHours(List<DailyStatistics> dailyStatisticsList) {
        double summedLightStateInSeconds = 0;
        for (DailyStatistics dailyStatistics : dailyStatisticsList) {
            summedLightStateInSeconds += dailyStatistics.getLightOnTimeInSeconds();
        }
        return summedLightStateInSeconds / 3600.0;
    }

    private int getNumberOfDaysWhenRadiatorWasOn(List<DailyStatistics> dailyStatistics) {
        int numberOfDaysWhenRadiatorWasOn = 0;
        for (DailyStatistics dailyStatistic : dailyStatistics) {
            if (dailyStatistic.getRadiatorOnTimeInSeconds() != 0) {
                numberOfDaysWhenRadiatorWasOn++;
            }
        }
        return numberOfDaysWhenRadiatorWasOn;
    }

}
