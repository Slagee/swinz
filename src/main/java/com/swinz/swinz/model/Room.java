package com.swinz.swinz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String name;
    private Double currentTemperature;
    private Double selectedTemperature;
    private double powerConsumption;
    private boolean radiatorState;
    private boolean lightState;
    private boolean radiatorForcedDown;

    @OneToMany
    private List<Report> reports;

    public Room() {
    }

    public Room(@JsonProperty("ID") Long ID, @JsonProperty("name") String name,
                @JsonProperty("currentTemperature") double currentTemperature, @JsonProperty("selectedTemperature") double selectedTemperature,
                @JsonProperty("powerConsumption") double powerConsumption, @JsonProperty("radiatorState") boolean radiatorState,
                @JsonProperty("lightState") boolean lightState, @JsonProperty("radiatorForcedDown") boolean radiatorForcedDown) {
        this.ID = ID;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.selectedTemperature = selectedTemperature;
        this.powerConsumption = powerConsumption;
        this.radiatorState = radiatorState;
        this.lightState = lightState;
        this.radiatorForcedDown = radiatorForcedDown;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getSelectedTemperature() {
        return selectedTemperature;
    }

    public void setSelectedTemperature(Double selectedTemperature) {
        this.selectedTemperature = selectedTemperature;
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

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public boolean isRadiatorForcedDown() {
        return radiatorForcedDown;
    }

    public void setRadiatorForcedDown(boolean radiatorForcedDown) {
        this.radiatorForcedDown = radiatorForcedDown;
    }

    @Override
    public String toString() {
        return "Room{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", currentTemperature=" + currentTemperature +
                ", selectedTemperature=" + selectedTemperature +
                ", powerConsumption=" + powerConsumption +
                ", radiatorState=" + radiatorState +
                ", lightState=" + lightState +
                '}';
    }
}
