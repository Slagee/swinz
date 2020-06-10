package com.swinz.swinz.manager;

import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TemperatureManager {

    private final RoomService roomService;

    @Autowired
    public TemperatureManager(RoomService roomService) {
        this.roomService = roomService;
    }

    public void raiseRoomTemperatureAndUpdateRoom(Room room) {
        double currentTemperature = room.getCurrentTemperature();
        room.setCurrentTemperature(raiseRoomTemperature(currentTemperature));
        room.setRadiatorState(true);
        roomService.updateRoom(room, room.getID());
    }

    public void keepRoomTemperatureAndUpdateRoom(Room room) {
        room.setRadiatorState(false);
        roomService.updateRoom(room, room.getID());
    }

    private double raiseRoomTemperature(double currentTemperature) {
        return BigDecimal.valueOf(currentTemperature).add(BigDecimal.valueOf(0.2)).doubleValue();
    }
}
