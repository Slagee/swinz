package com.swinz.swinz.runners;

import com.swinz.swinz.dao.DailyStatisticsRepository;
import com.swinz.swinz.dao.RoomRepository;
import com.swinz.swinz.model.DailyStatistics;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatabaseInitRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final DailyStatisticsRepository dailyStatisticsRepository;
    private final ReportService reportService;

    public DatabaseInitRunner(RoomRepository roomRepository, DailyStatisticsRepository dailyStatisticsRepository, ReportService reportService) {
        this.roomRepository = roomRepository;
        this.dailyStatisticsRepository = dailyStatisticsRepository;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {

        var rooms = IntStream.range(1, 2).mapToObj(num -> {
            Room r = new Room();

            r.setName("Test " + num);
            r.setSelectedTemperature(23.0);

            return r;
        }).collect(Collectors.toList());

        roomRepository.saveAll(rooms);

        Room room = new Room();
        room.setName("TestRoomJedna");
        room.setSelectedTemperature(18.0);
        roomRepository.save(room);

        DailyStatistics dailyStatistics = new DailyStatistics();
        dailyStatistics.setDate(LocalDate.of(2020, 6, 22));
        dailyStatistics.setRoom(room);
        dailyStatistics.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics);

        DailyStatistics dailyStatistics2 = new DailyStatistics();
        dailyStatistics2.setDate(LocalDate.of(2020, 6, 23));
        dailyStatistics2.setRoom(room);
        dailyStatistics2.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics2);

        DailyStatistics dailyStatistics3 = new DailyStatistics();
        dailyStatistics3.setDate(LocalDate.of(2020, 6, 24));
        dailyStatistics3.setRoom(room);
        dailyStatistics3.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics3);

        DailyStatistics dailyStatistics4 = new DailyStatistics();
        dailyStatistics4.setDate(LocalDate.of(2020, 6, 25));
        dailyStatistics4.setRoom(room);
        dailyStatistics4.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics4);

        DailyStatistics dailyStatistics5 = new DailyStatistics();
        dailyStatistics5.setDate(LocalDate.of(2020, 6, 26));
        dailyStatistics5.setRoom(room);
        dailyStatistics5.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics5);

        DailyStatistics dailyStatistics6 = new DailyStatistics();
        dailyStatistics6.setDate(LocalDate.of(2020, 6, 27));
        dailyStatistics6.setRoom(room);
        dailyStatistics6.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics6);

        DailyStatistics dailyStatistics7 = new DailyStatistics();
        dailyStatistics7.setDate(LocalDate.of(2020, 6, 28));
        dailyStatistics7.setRoom(room);
        dailyStatistics7.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics7);

        DailyStatistics dailyStatistics8 = new DailyStatistics();
        dailyStatistics8.setDate(LocalDate.of(2020, 6, 29));
        dailyStatistics8.setRoom(room);
        dailyStatistics8.setLightOnTimeInSeconds(25200);
        dailyStatisticsRepository.save(dailyStatistics8);

        Report report = new Report();
        report.setReportDate(LocalDate.now().minusDays(1));
        report.setRoom(room);
        reportService.addReport(report);

        Report report2 = new Report();
        report2.setReportDate(LocalDate.now().minusDays(1));
        report2.setRoom(room);
        reportService.addReport(report);

    }

}