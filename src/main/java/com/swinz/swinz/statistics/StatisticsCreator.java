package com.swinz.swinz.statistics;

import com.swinz.swinz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsCreator {

    private final ReportService reportService;

    @Autowired
    public StatisticsCreator(ReportService reportService) {
        this.reportService = reportService;
    }

//    @Async
//    @Scheduled(fixedRate = 60000)
//    public void createDailyStatistics(){
//        List<Report> dailyReports = reportService.findReportsByLocalDate(LocalDate.now());
//    }


}
