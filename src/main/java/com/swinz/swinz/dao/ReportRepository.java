package com.swinz.swinz.dao;

import com.swinz.swinz.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {
    List<Report> findByRoomID(int id);

    List<Report> findReportsByReportDate(LocalDate date);
}
