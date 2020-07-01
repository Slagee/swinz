package com.swinz.swinz.report.manager;

import com.swinz.swinz.manager.TemperatureManager;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.report.generator.ReportGenerator;
import com.swinz.swinz.service.DailyStatisticsService;
import com.swinz.swinz.service.ReportService;
import com.swinz.swinz.service.RoomService;
import com.swinz.swinz.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.swinz.swinz.constants.Constants.TEMPERATURE_THRESHOLD;

@Component
public class ReportManager {

    private final ReportService reportService;
    private final RoomService roomService;
    private final TemperatureManager temperatureManager;
    private final DailyStatisticsService dailyStatisticsService;

    @Autowired
    public ReportManager(ReportService reportService, RoomService roomService, TemperatureManager temperatureManager, DailyStatisticsService dailyStatisticsService) {
        this.reportService = reportService;
        this.roomService = roomService;
        this.temperatureManager = temperatureManager;
        this.dailyStatisticsService = dailyStatisticsService;
    }

    @Async
    @Scheduled(fixedRate = 3000)
    public void createAndManageReport() {
        for (Room room : roomService.getAllRooms()) {
            Room roomWithGeneratedValues = ReportGenerator.generateRoomSensorValues(room);
            manageRoomTemperature(roomWithGeneratedValues);
            Report report = createReportFromRoomValues(roomWithGeneratedValues);
            reportService.addReport(report);
            System.out.println(report);
        }
    }

    private Report createReportFromRoomValues(Room room) {
        Report report = new Report();
        report.setReportTime(LocalTime.now());
        report.setReportDate(LocalDate.now());
        report.setRoom(room);
        report.setRadiatorState(room.getRadiatorState());
        report.setLightState(room.getLightState());
        report.setPowerConsumption(room.getPowerConsumption());
        report.setCurrentTemperature(room.getCurrentTemperature());
        report.setSelectedTemperature(room.getSelectedTemperature());
        return report;
    }

    private void manageRoomTemperature(Room room) {
        if (room.isRadiatorForcedDown()) {
            temperatureManager.lowerToBasicRoomTemperatureAndUpdateRoom(room);
        } else {
            if (Utils.compareWithThreshold(room.getCurrentTemperature(), room.getSelectedTemperature(), TEMPERATURE_THRESHOLD)) {
                temperatureManager.keepRoomTemperatureAndUpdateRoom(room);
            } else {
                temperatureManager.changeRoomTemperatureAndUpdateRoom(room);
            }
        }
    }
}
