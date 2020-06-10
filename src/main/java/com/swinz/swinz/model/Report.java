package com.swinz.swinz.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private LocalDate reportDate;
    private LocalTime reportTime;

    @ManyToOne()
    private Room room;

    public Report() {
    }

    public Report(Integer ID, LocalDateTime reportDateTime, LocalTime reportTime, Room room) {
        this.ID = ID;
        this.reportTime = reportTime;
        this.room = room;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer id) {
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

    @Override
    public String toString() {
        return "Report{" +
                "id=" + ID +
                "date=" + reportDate +
                "time=" + reportTime +
                ", room=" + room.toString() +
                '}';
    }
}
