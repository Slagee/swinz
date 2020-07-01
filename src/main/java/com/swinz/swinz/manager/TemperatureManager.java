package com.swinz.swinz.manager;

import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.RoomService;
import com.swinz.swinz.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.swinz.swinz.constants.Constants.TEMPERATURE_RAISE;
import static com.swinz.swinz.constants.Constants.TEMPERATURE_THRESHOLD;

@Component
public class TemperatureManager {

    private final RoomService roomService;

    @Autowired
    public TemperatureManager(RoomService roomService) {
        this.roomService = roomService;
    }

    public void changeRoomTemperatureAndUpdateRoom(Room room) {
        double currentTemperature = room.getCurrentTemperature();
        if (room.getSelectedTemperature() > room.getCurrentTemperature()) {
            room.setCurrentTemperature(raiseRoomTemperature(currentTemperature));
        } else {
            room.setCurrentTemperature(lowerRoomTemperature(currentTemperature));
        }
        room.setRadiatorState(true);
        roomService.updateRoom(room, room.getID());
    }

    public void lowerToBasicRoomTemperatureAndUpdateRoom(Room room) {
        if (!Utils.compareWithThreshold(room.getCurrentTemperature(), 18, TEMPERATURE_THRESHOLD)) {
            room.setCurrentTemperature(lowerRoomTemperature(room.getCurrentTemperature()));
        }
        room.setRadiatorState(false);
        roomService.updateRoom(room, room.getID());
    }

    public void keepRoomTemperatureAndUpdateRoom(Room room) {
        room.setRadiatorState(false);
        roomService.updateRoom(room, room.getID());
    }

    private double lowerRoomTemperature(double currentTemperature) {
        return BigDecimal.valueOf(currentTemperature).subtract(BigDecimal.valueOf(TEMPERATURE_RAISE)).doubleValue();
    }

    private double raiseRoomTemperature(double currentTemperature) {
        return BigDecimal.valueOf(currentTemperature).add(BigDecimal.valueOf(TEMPERATURE_RAISE)).doubleValue();
    }
}
