package com.swinz.swinz.service;

import com.swinz.swinz.dao.DailyStatisticsRepository;
import com.swinz.swinz.model.DailyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DailyStatisticsService {

    private final DailyStatisticsRepository dailyStatisticsRepository;

    @Autowired
    public DailyStatisticsService(DailyStatisticsRepository dailyStatisticsRepository) {
        this.dailyStatisticsRepository = dailyStatisticsRepository;
    }

    public List<DailyStatistics> getAllDailyStatistics() {
        List<DailyStatistics> dailyStatistics = new ArrayList<>();
        dailyStatisticsRepository.findAll().forEach(dailyStatistics::add);
        return dailyStatistics;
    }

    public DailyStatistics getDailyStatisticsByID(long id) {
        return dailyStatisticsRepository.findById(id).orElseThrow();
    }

    public List<DailyStatistics> getDailyStatisticsByRoomIDAndDateBetween(long id, LocalDate first, LocalDate second) {
        return dailyStatisticsRepository.findDailyStatisticsByRoom_IDAndDateBetween(id, first, second);
    }

    public void updateDailyStatistics(DailyStatistics dailyStatistics, long id) {
        dailyStatistics.setID(id);
        dailyStatisticsRepository.save(dailyStatistics);
    }

    public void deleteDailyStatistics(DailyStatistics dailyStatistics) {
        dailyStatisticsRepository.delete(dailyStatistics);
    }

    public void deleteDailyStatisticsByID(long id) {
        dailyStatisticsRepository.deleteById(id);
    }

    public void addDailyStatistics(DailyStatistics dailyStatistics) {
        dailyStatisticsRepository.save(dailyStatistics);
    }
}
