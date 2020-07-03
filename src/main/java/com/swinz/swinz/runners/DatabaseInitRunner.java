package com.swinz.swinz.runners;

import com.swinz.swinz.model.DailyStatistics;
import com.swinz.swinz.model.Room;
import com.swinz.swinz.service.DailyStatisticsService;
import com.swinz.swinz.service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class DatabaseInitRunner implements CommandLineRunner {

    private final RoomService roomService;
    private final DailyStatisticsService dailyStatisticsService;

    Random r = new Random();

    public DatabaseInitRunner(RoomService roomService, DailyStatisticsService dailyStatisticsService) {
        this.roomService = roomService;
        this.dailyStatisticsService = dailyStatisticsService;
    }

    @Override
    public void run(String... args) {
        List<Room> roomList = new ArrayList<>();
        Room room1 = new Room();
        room1.setSelectedTemperature(22.0);
        room1.setCurrentTemperature(18);
        room1.setName("Room1");
        roomService.addRoom(room1);
        roomList.add(room1);

        Room room2 = new Room();
        room2.setSelectedTemperature(23.0);
        room2.setCurrentTemperature(17.5);
        room2.setName("Room2");
        roomService.addRoom(room2);
        roomList.add(room2);

        for (LocalDate date : getDatesBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 7, 3))) {
            for (Room room : roomList) {
                DailyStatistics dailyStatistics = randomlyGenerateStatistics();
                dailyStatistics.setRoom(room);
                dailyStatistics.setDate(date);
                dailyStatistics.setMonth(date.getMonth().getValue());
                dailyStatisticsService.addDailyStatistics(dailyStatistics);
            }
        }
    }

    private DailyStatistics randomlyGenerateStatistics() {
        DailyStatistics dailyStatistics = new DailyStatistics();
        dailyStatistics.setLightOnTimeInSeconds(generateRandomLightTime());
        dailyStatistics.setRadiatorOnTimeInSeconds(generateRandomRadiatorTime());
        dailyStatistics.setPowerConsumption(generateRandomPowerConsumption());
        return dailyStatistics;
    }

    private int generateRandomLightTime() {
        return r.nextInt((28800 - 18000) + 1) + 18000;
    }

    private int generateRandomRadiatorTime() {
        return r.nextInt((75600 - 64800) + 1) + 64800;
    }

    private int generateRandomPowerConsumption() {
        return r.nextInt((11000 - 9000) + 1) + 9000;
    }

    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }
}