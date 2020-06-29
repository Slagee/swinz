package com.swinz.swinz.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private Double currentTemperature;
    private Double selectedTemperature;
    private double powerConsumption;
    private boolean radiatorState;
    private boolean lightState;
    private LocalDate reportDate;
    private LocalTime reportTime;

    @ManyToOne()
    private Room room;

    public Report() {
    }

    public Report(Long ID, boolean radiatorState, boolean lightState, LocalTime reportTime) {
        this.ID = ID;
        this.radiatorState = radiatorState;
        this.lightState = lightState;
        this.reportTime = reportTime;
    }

    public Report(Long ID, LocalDateTime reportDateTime, Double currentTemperature, Double selectedTemperature,
                  double powerConsumption, boolean radiatorState, boolean lightState, LocalTime reportTime, Room room) {
        this.ID = ID;
        this.currentTemperature = currentTemperature;
        this.selectedTemperature = selectedTemperature;
        this.powerConsumption = powerConsumption;
        this.radiatorState = radiatorState;
        this.lightState = lightState;
        this.reportTime = reportTime;
        this.room = room;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalTime reportTime) {
        this.reportTime = reportTime;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getSelectedTemperature() {
        return selectedTemperature;
    }

    public void setSelectedTemperature(Double selectedTemperature) {
        this.selectedTemperature = selectedTemperature;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public boolean getRadiatorState() {
        return radiatorState;
    }

    public void setRadiatorState(boolean radiatorState) {
        this.radiatorState = radiatorState;
    }

    public boolean getLightState() {
        return lightState;
    }

    public void setLightState(boolean lightState) {
        this.lightState = lightState;
    }


    @Override
    public String toString() {
        return "Report{" +
                "ID=" + ID +
                ", currentTemperature=" + currentTemperature +
                ", selectedTemperature=" + selectedTemperature +
                ", powerConsumption=" + powerConsumption +
                ", radiatorState=" + radiatorState +
                ", lightState=" + lightState +
                ", reportDate=" + reportDate +
                ", reportTime=" + reportTime +
                '}';
    }
}
