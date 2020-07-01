package com.swinz.swinz.api;

import com.swinz.swinz.service.StatisticsPseudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private final StatisticsPseudoService statisticsPseudoService;

    @Autowired
    public StatisticsController(StatisticsPseudoService statisticsPseudoService) {
        this.statisticsPseudoService = statisticsPseudoService;
    }

    @RequestMapping("/stats/weeklyLight/{id}")
    public double getAverageLightStateForLastWeekByRoomID(@PathVariable long id) {
        return statisticsPseudoService.getAverageLightStateForLastWeekByRoomID(id);
    }

    @RequestMapping("/stats/yearlyDaysOfRadiator")
    public int getNumberOfDaysInYearWhenRadiatorWasOn() {
        return statisticsPseudoService.getNumberOfDaysInYearWhenRadiatorWasOn();
    }

    @RequestMapping("/stats/monthlyDaysOfRadiator/{roomID}/{monthValue}")
    public int getNumberOfDaysInMonthWhenRadiatorWasOn(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsPseudoService.getNumberOfDaysInMonthWhenRadiatorWasOn(roomID, monthValue);
    }

    @RequestMapping("stats/monthlyLight/{roomID}/{monthValue}")
    public double getAverageLightStateByRoomIDAndMonthValue(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsPseudoService.getAverageLightStateByRoomIDAndMonthValue(roomID, monthValue);
    }

    @RequestMapping("stats/monthlyPower/{roomID}/{monthValue}")
    public int getMonthlyPowerConsumptionByRoomIDAndMonthValue(@PathVariable long roomID, @PathVariable int monthValue) {
        return statisticsPseudoService.getMonthlyPowerConsumptionByRoomIDAndMonthValue(roomID, monthValue);
    }
}
