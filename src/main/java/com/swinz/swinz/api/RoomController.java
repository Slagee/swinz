package com.swinz.swinz.api;

import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @RequestMapping("/rooms/{id}")
    public Room getRoomByID(@PathVariable Long id) {
        return roomService.getRoomByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    public void addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rooms/{id}")
    public void updateRoom(@RequestBody Room room, @PathVariable Long id) {
        roomService.updateRoom(room, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{id}")
    public void deleteRoomByID(@PathVariable Long id) {
        roomService.deleteRoomByID(id);
    }
}