package com.swinz.swinz.sensor;

import com.swinz.swinz.model.Room;

public class PowerConsumptionSensor {
    public static int generatePowerConsumption(Room room) {
        int returned = 19;
        if (room.getLightState()) {
            returned += 2;
        }
        if (room.getRadiatorState()) {
            returned += 2;
        }
        return returned;
    }
}
