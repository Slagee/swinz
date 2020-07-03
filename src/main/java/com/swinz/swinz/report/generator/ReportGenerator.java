package com.swinz.swinz.report.generator;

import com.swinz.swinz.model.Room;
import com.swinz.swinz.sensor.LightStateSensor;
import com.swinz.swinz.sensor.PowerConsumptionSensor;
import com.swinz.swinz.sensor.TemperatureSensor;

public class ReportGenerator {

    public static Room generateRoomSensorValues(Room room) {
        room.setCurrentTemperature(TemperatureSensor.generateTemperature(room));
        room.setLightState(LightStateSensor.generateLightState(room));
        room.setPowerConsumption(PowerConsumptionSensor.generatePowerConsumption(room));
        return room;
    }

}
