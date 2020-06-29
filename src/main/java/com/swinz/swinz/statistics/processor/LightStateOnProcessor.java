package com.swinz.swinz.statistics.processor;

import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.Report;

public class LightStateOnProcessor extends ReportProcessor {

    @Override
    public void processReport(Report report, TimeTypeEnum timeTypeEnum) {

        if (reportStack.getFirst() != null || report.getLightState()) {
            reportStack.addReport(report);

            if (isReadyForCalculation()) {
                calculateTime(timeTypeEnum);
            }
        }
    }

    @Override
    public long getTotalTime(TimeTypeEnum timeTypeEnum) {
        if (reportStack.isFull()) {
            calculateTime(timeTypeEnum);
        }

        return totalTimeInSeconds;
    }

    @Override
    protected boolean isReadyForCalculation() {
        return reportStack.isFull() && !reportStack.getSecond().getLightState();
    }

}