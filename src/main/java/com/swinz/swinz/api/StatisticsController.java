package com.swinz.swinz.api;

import com.swinz.swinz.statistics.StatisticsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private final StatisticsUtil statisticsUtil;

    @Autowired
    public StatisticsController(StatisticsUtil statisticsUtil) {
        this.statisticsUtil = statisticsUtil;
    }

    @RequestMapping("/stats/weeklyLight/{id}")
    public double getAverageLightStateForLastWeekByRoomID(@PathVariable long id) {
        return statisticsUtil.getAverageLightStateForLastWeekByRoomID(id);
    }

    @RequestMapping("/stats/yearlyDaysOfRadiator")
    public int getNumberOfDaysInYearWhenRadiatorWasOn() {
        return statisticsUtil.getNumberOfDaysWhenRadiatorWasOn();
    }

    @RequestMapping("/stats/monthlyDaysOfRadiator/{roomID}/{monthValue}")
    public int getNumberOfDaysInMonthWhenRadiatorWasOn(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsUtil.getNumberOfDaysInMonthWhenRadiatorWasOn(roomID, monthValue);
    }

    @RequestMapping("stats/monthlyLight/{roomID}/{monthValue}")
    public double getAverageLightStateByRoomIDAndMonthValue(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsUtil.getAverageLightStateByRoomIDAndMonthValue(roomID, monthValue);
    }

    @RequestMapping("stats/monthlyPower/{roomID}/{monthValue}")
    public int getMonthlyPowerConsumptionByRoomIDAndMonthValue(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsUtil.getMonthlyPowerConsumptionByRoomIDAndMonthValue(roomID, monthValue);
    }
}
