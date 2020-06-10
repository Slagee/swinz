package com.swinz.swinz.report.generator;

import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.sensor.LightStateSensor;
import com.swinz.swinz.sensor.TemperatureSensor;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportGenerator {

    public static Report generateReport(Room room) {
        Report report = new Report();
        report.setRoom(room);
        room.setCurrentTemperature(TemperatureSensor.generateTemperature(room));
        room.setLightState(LightStateSensor.generateLightState(room));
        room.setPowerConsumption(80);
        report.setReportDate(LocalDate.now());
        report.setReportTime(LocalTime.now());
        return report;
    }

}
