package com.swinz.swinz.dao;

import com.swinz.swinz.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findRoomsByHouse_ID(int id);
}
