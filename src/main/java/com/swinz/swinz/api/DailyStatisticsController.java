package com.swinz.swinz.api;

import com.swinz.swinz.model.DailyStatistics;
import com.swinz.swinz.service.DailyStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailyStatisticsController {

    private final DailyStatisticsService dailyStatisticsService;

    @Autowired
    public DailyStatisticsController(DailyStatisticsService dailyStatisticsService) {
        this.dailyStatisticsService = dailyStatisticsService;
    }

    @RequestMapping("/stats")
    public List<DailyStatistics> getAllDailyStatistics() {
        return dailyStatisticsService.getAllDailyStatistics();
    }

    @RequestMapping("/stats/{id}")
    public DailyStatistics getDailyStatisticsByID(@PathVariable long id) {
        return dailyStatisticsService.getDailyStatisticsByID(id);
    }
}
