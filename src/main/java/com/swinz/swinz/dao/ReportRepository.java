package com.swinz.swinz.dao;

import com.swinz.swinz.model.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByRoomID(long id);

    List<Report> findReportsByReportDate(LocalDate date);

    List<Report> findReportsByReportDateAndRoom_ID(LocalDate date, long roomID);

    void deleteReportsByReportDate(LocalDate date);
}
