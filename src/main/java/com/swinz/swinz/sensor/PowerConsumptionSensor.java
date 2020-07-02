package com.swinz.swinz.sensor;

import com.swinz.swinz.model.Room;

public class PowerConsumptionSensor {
    public static int generatePowerConsumption(Room room) {
        int returned = 70;
        if (room.getLightState()) {
            returned += 10;
        }
        if (room.getRadiatorState()) {
            returned += 10;
        }
        return returned;
    }
}
