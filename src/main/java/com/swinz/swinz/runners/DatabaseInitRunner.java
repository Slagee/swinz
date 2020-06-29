package com.swinz.swinz.runners;

import com.swinz.swinz.dao.RoomRepository;
import com.swinz.swinz.model.Room;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatabaseInitRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;

    public DatabaseInitRunner(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var rooms = IntStream.range(1, 2).mapToObj(num -> {
            Room r = new Room();

            r.setName("Test " + num);
            r.setSelectedTemperature(23.0);

            return r;
        }).collect(Collectors.toList());

        roomRepository.saveAll(rooms);

    }

}