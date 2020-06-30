package com.swinz.swinz.dao;

import com.swinz.swinz.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByRoomID(Long id);

    List<Report> findReportsByReportDate(LocalDate date);

    List<Report> findReportsByReportDateAndRoom_ID(LocalDate date, Long roomID);

    void deleteReportsByReportDate(LocalDate date);
}
