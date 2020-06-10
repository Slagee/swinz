package com.swinz.swinz.report.manager;

import com.swinz.swinz.manager.TemperatureManager;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.report.generator.ReportGenerator;
import com.swinz.swinz.service.ReportService;
import com.swinz.swinz.service.RoomService;
import com.swinz.swinz.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.swinz.swinz.constants.Constants.TEMPERATURE_THRESHOLD;

@Component
public class ReportManager {

    private final ReportService reportService;
    private final RoomService roomService;
    private final TemperatureManager temperatureManager;

    @Autowired
    public ReportManager(ReportService reportService, RoomService roomService, TemperatureManager temperatureManager) {
        this.reportService = reportService;
        this.roomService = roomService;
        this.temperatureManager = temperatureManager;

        Room room0 = new Room();
        room0.setID(1);
        room0.setName("Test");
        room0.setSelectedTemperature(23.0);
        Room room1 = new Room();
        room1.setID(2);
        room1.setName("Test2");
        room1.setSelectedTemperature(23.0);
        roomService.addRoom(room0);
        roomService.addRoom(room1);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void manageReport() {
        for (Room room : roomService.getAllRooms()) {
            Report report = ReportGenerator.generateReport(room);
            manageRoomTemperature(report.getRoom());
            reportService.addReport(report);
            System.out.println(report);
        }
    }

    private void manageRoomTemperature(Room room) {
        if (room.getCurrentTemperature() < room.getSelectedTemperature()) {
            temperatureManager.raiseRoomTemperatureAndUpdateRoom(room);
        } else if (Utils.compareWithThreshold(room.getCurrentTemperature(), room.getSelectedTemperature(), TEMPERATURE_THRESHOLD)) {
            temperatureManager.keepRoomTemperatureAndUpdateRoom(room);
        }
    }
}
