package com.swinz.swinz.service;

import com.swinz.swinz.dao.ReportRepository;
import com.swinz.swinz.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        reportRepository.findAll().forEach(reports::add);
        return reports;
    }

    public Report getReportByID(Long id) {
        return reportRepository.findById(id).orElseThrow();
    }

    public void addReport(Report report) {
        reportRepository.save(report);
    }

    public void updateReport(Report report, Long id) {
        report.setID(id);
        reportRepository.save(report);
    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }

    public void deleteReportByID(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getReportsByRoomID(Long id) {
        return reportRepository.findByRoomID(id);
    }

    public List<Report> getReportsByLocalDate(LocalDate localDate) {
        return reportRepository.findReportsByReportDate(localDate);
    }

    public List<Report> getReportsByLocalDateAndRoomID(LocalDate localDate, Long roomID) {
        return reportRepository.findReportsByReportDateAndRoom_ID(localDate, roomID);
    }

    public void deleteReportsByLocalDate(LocalDate localDate) {
        reportRepository.deleteReportsByReportDate(localDate);
    }
}
