package com.swinz.swinz.statistics.processor;


import com.swinz.swinz.model.Report;

public interface ReportProcessor {


    void processReport(Report report);

    long getTotalTime();


}
