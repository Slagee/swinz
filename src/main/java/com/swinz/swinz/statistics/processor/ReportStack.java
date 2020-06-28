package com.swinz.swinz.statistics.processor;


import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.Report;

import java.time.Duration;

public final class ReportStack {

    private final static int FIRST_INDEX = 0;
    private final static int SECOND_INDEX = 1;

    private Report[] reports = new Report[2];

    public void addReport(Report report) {
        if (getFirst() == null) {
            reports[FIRST_INDEX] = report;
        } else {

            reports[SECOND_INDEX] = report;
        }
    }

    public Report getFirst() {
        return reports[FIRST_INDEX];
    }

    public Report getSecond() {
        return reports[SECOND_INDEX];
    }

    public void clear() {
        reports[FIRST_INDEX] = reports[SECOND_INDEX] = null;
    }

    public boolean isFull() {
        return getFirst() != null && getSecond() != null;
    }

    public Long getDurationBetweenReports(TimeTypeEnum timeTypeEnum) {
        Duration returnedDuration = Duration.between(getFirst().getReportTime(), getSecond().getReportTime());
        long returnedValue = 0;
        switch (timeTypeEnum) {
            case HOUR:
                returnedValue = returnedDuration.toHours();
                break;
            case MINUTE:
                returnedValue = returnedDuration.toMinutes();
                break;
            case SECOND:
                returnedValue = returnedDuration.toSeconds();
                break;
        }
        return returnedValue;
    }
}
