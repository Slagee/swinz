package com.swinz.swinz.report.generator;

import com.swinz.swinz.model.Report;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.sensor.LightStateSensor;
import com.swinz.swinz.sensor.TemperatureSensor;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportGenerator {

    public static Report generateReport(Room room) {
        room.setCurrentTemperature(TemperatureSensor.generateTemperature(room));
        room.setLightState(LightStateSensor.generateLightState(room));
        room.setPowerConsumption(80);

        Report report = new Report();
        report.setRoom(room);
        report.setCurrentTemperature(room.getCurrentTemperature());
        report.setSelectedTemperature(room.getSelectedTemperature());
        report.setRadiatorState(room.getRadiatorState());
        report.setLightState(room.getLightState());
        report.setPowerConsumption(room.getPowerConsumption());
        report.setReportDate(LocalDate.now());
        report.setReportTime(LocalTime.now());
        return report;
    }

    public static Room generateSensorValues(Room room) {
        room.setCurrentTemperature(TemperatureSensor.generateTemperature(room));
        room.setLightState(LightStateSensor.generateLightState(room));
        room.setPowerConsumption(80);
        return room;
    }

}
