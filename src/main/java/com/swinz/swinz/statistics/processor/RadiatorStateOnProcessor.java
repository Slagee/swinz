package com.swinz.swinz.statistics.processor;

import com.swinz.swinz.model.Report;

public class RadiatorStateOnProcessor implements ReportProcessor {

    private long totalTimeInHours;
    private ReportStack reportStack = new ReportStack();

    @Override
    public void processReport(Report report) {

        if (reportStack.getFirst() != null || report.getRadiatorState()) {
            reportStack.addReport(report);

            if (isReadyForCalculation()) {
                calculateTime();
            }
        }
    }

    @Override
    public long getTotalTime() {

        if (reportStack.isFull()) {
            calculateTime();
        }

        return totalTimeInHours;
    }

    private void calculateTime() {
        totalTimeInHours += reportStack.getDurationInSecondsBetweenReports();
        reportStack.clear();
    }

    private boolean isReadyForCalculation() {
        return reportStack.isFull() && !reportStack.getSecond().getRadiatorState();
    }
}
