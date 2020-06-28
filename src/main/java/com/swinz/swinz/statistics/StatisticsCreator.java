package com.swinz.swinz.statistics;

import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.service.ReportService;
import com.swinz.swinz.statistics.processor.LightStateOnProcessor;
import com.swinz.swinz.statistics.processor.RadiatorStateOnProcessor;
import com.swinz.swinz.statistics.processor.ReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StatisticsCreator {

    private final ReportService reportService;

    @Autowired
    public StatisticsCreator(ReportService reportService) {
        this.reportService = reportService;
    }

    @Async
    @Scheduled(fixedRate = 10000)
    public void createDailyStatistics() {
        List<Report> dailyReports = reportService.getReportsByLocalDate(LocalDate.now());
        ReportProcessor reportProcessor = new RadiatorStateOnProcessor();

        for (Report report : dailyReports) {
            reportProcessor.processReport(report, TimeTypeEnum.SECOND);
        }

        reportProcessor.getTotalTime(TimeTypeEnum.SECOND);

        ReportProcessor lightStateOnProcessor = new LightStateOnProcessor();

        for (Report report : dailyReports) {
            lightStateOnProcessor.processReport(report, TimeTypeEnum.SECOND);
        }

        lightStateOnProcessor.getTotalTime(TimeTypeEnum.SECOND);
    }
}
