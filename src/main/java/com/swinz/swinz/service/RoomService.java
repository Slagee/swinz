package com.swinz.swinz.service;

import com.swinz.swinz.dao.RoomRepository;
import com.swinz.swinz.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    public Room getRoomByID(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Room room, Long id) {
        room.setID(id);
        roomRepository.save(room);
    }

    public void deleteRoomByID(Long id) {
        roomRepository.deleteById(id);
    }

}
