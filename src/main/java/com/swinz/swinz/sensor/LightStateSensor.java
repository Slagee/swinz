package com.swinz.swinz.sensor;

import com.swinz.swinz.model.Room;

public class LightStateSensor {

    public static boolean generateLightState(Room room) {
        return room.getLightState();
    }
}
