package com.swinz.swinz.statistics;

import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.DailyStatistics;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.DailyStatisticsService;
import com.swinz.swinz.service.ReportService;
import com.swinz.swinz.service.RoomService;
import com.swinz.swinz.statistics.processor.LightStateOnProcessor;
import com.swinz.swinz.statistics.processor.RadiatorStateOnProcessor;
import com.swinz.swinz.statistics.processor.ReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyStatisticsCreator {

    private final ReportService reportService;
    private final DailyStatisticsService dailyStatisticsService;
    private final RoomService roomService;

    private final ReportProcessor radiatorStateOnProcessor = new RadiatorStateOnProcessor();
    private final ReportProcessor lightStateOnProcessor = new LightStateOnProcessor();

    @Autowired
    public DailyStatisticsCreator(ReportService reportService, DailyStatisticsService dailyStatisticsService, RoomService roomService) {
        this.reportService = reportService;
        this.dailyStatisticsService = dailyStatisticsService;
        this.roomService = roomService;
    }

    //cron = "1 0 0 * * *"
    //        sec min hour dom month dow
    @Async
    @Transactional
    @Scheduled(cron = "1 0 0 * * ?")
    public void createDailyStatistics() {
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        for (Room room : roomService.getAllRooms()) {
            List<Report> dailyReports = reportService.getReportsByLocalDateAndRoomID(yesterdayDate, room.getID());
            for (Report report : dailyReports) {
                radiatorStateOnProcessor.processReport(report, TimeTypeEnum.SECOND);
                lightStateOnProcessor.processReport(report, TimeTypeEnum.SECOND);
            }
            DailyStatistics dailyStatistics = new DailyStatistics();
            dailyStatistics.setRoom(room);
            dailyStatistics.setDate(yesterdayDate);
            dailyStatistics.setLightOnTimeInSeconds(lightStateOnProcessor.getTotalTime(TimeTypeEnum.SECOND));
            dailyStatistics.setRadiatorOnTimeInSeconds(radiatorStateOnProcessor.getTotalTime(TimeTypeEnum.SECOND));
            dailyStatistics.setPowerConsumption(getTotalPowerConsumption(dailyReports));

            dailyStatisticsService.addDailyStatistics(dailyStatistics);
        }
        reportService.deleteReportsByLocalDate(yesterdayDate);
    }

    private int getTotalPowerConsumption(List<Report> reportList) {
        int returnedSum = 0;
        for (Report report : reportList) {
            returnedSum += report.getPowerConsumption();
        }
        return returnedSum;
    }
}
