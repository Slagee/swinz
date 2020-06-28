package com.swinz.swinz.statistics.processor;


import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.Report;

public abstract class ReportProcessor {

    protected long totalTimeInSeconds;
    protected ReportStack reportStack = new ReportStack();

    public abstract void processReport(Report report, TimeTypeEnum timeTypeEnum);

    public abstract long getTotalTime(TimeTypeEnum timeTypeEnum);

    protected abstract boolean isReadyForCalculation();

    protected void calculateTime(TimeTypeEnum timeTypeEnum) {
        totalTimeInSeconds += reportStack.getDurationBetweenReports(timeTypeEnum);
        reportStack.clear();
    }
}
