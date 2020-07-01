package com.swinz.swinz.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DailyStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private double radiatorOnTimeInSeconds;
    private double lightOnTimeInSeconds;
    private double powerConsumption;
    private LocalDate date;
    private int month;

    @ManyToOne
    private Room room;

    public DailyStatistics() {
    }

    public DailyStatistics(Long ID, double radiatorOnTimeInSeconds, double lightOnTimeInSeconds, double powerConsumption, LocalDate date, Room room) {
        this.ID = ID;
        this.radiatorOnTimeInSeconds = radiatorOnTimeInSeconds;
        this.lightOnTimeInSeconds = lightOnTimeInSeconds;
        this.powerConsumption = powerConsumption;
        this.date = date;
        this.room = room;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public double getRadiatorOnTimeInSeconds() {
        return radiatorOnTimeInSeconds;
    }

    public void setRadiatorOnTimeInSeconds(double radiatorOnTimeInSeconds) {
        this.radiatorOnTimeInSeconds = radiatorOnTimeInSeconds;
    }

    public double getLightOnTimeInSeconds() {
        return lightOnTimeInSeconds;
    }

    public void setLightOnTimeInSeconds(double lightOnTimeInSeconds) {
        this.lightOnTimeInSeconds = lightOnTimeInSeconds;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        setMonth(date.getMonth().getValue());
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
