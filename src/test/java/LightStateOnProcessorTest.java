import com.swinz.swinz.enums.TimeTypeEnum;
import com.swinz.swinz.model.Report;
import com.swinz.swinz.statistics.processor.LightStateOnProcessor;
import com.swinz.swinz.statistics.processor.ReportProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.util.List;

public class LightStateOnProcessorTest {


    @Test
    void testCase1() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, false, false, LocalTime.of(6, 0))
        );

        Assert.assertEquals(2, getTotalTimeFromReports(reports));
    }

    @Test
    void testCase2() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, false, false, LocalTime.of(6, 0))
        );


        Assert.assertEquals(3, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase3() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, true, true, LocalTime.of(4, 0)),
                new Report(5L, false, false, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );

        Assert.assertEquals(2, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase4() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, false, false, LocalTime.of(6, 0))
        );


        Assert.assertEquals(3, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase5() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, false, false, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(7, 0))
        );

        Assert.assertEquals(2, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase6() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, true, true, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );


        Assert.assertEquals(4, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase7() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, false, false, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(7, 0))
        );


        Assert.assertEquals(2, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase8() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, true, true, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, true, true, LocalTime.of(4, 0)),
                new Report(5L, false, false, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );


        Assert.assertEquals(4, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase9() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, true, true, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, true, true, LocalTime.of(4, 0)),
                new Report(5L, false, false, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );


        Assert.assertEquals(4, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase10() {
        List<Report> reports = List.of(
                new Report(1L, true, true, LocalTime.of(1, 0)),
                new Report(2L, true, true, LocalTime.of(2, 0)),
                new Report(3L, true, true, LocalTime.of(3, 0)),
                new Report(4L, true, true, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );


        Assert.assertEquals(5, getTotalTimeFromReports(reports));
    }

    @Test
    void testCase11() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, false, false, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, false, false, LocalTime.of(5, 0)),
                new Report(6L, false, false, LocalTime.of(6, 0))
        );

        Assert.assertEquals(0, getTotalTimeFromReports(reports));
    }


    @Test
    void testCase12() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, false, false, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, false, false, LocalTime.of(6, 0))
        );

        Assert.assertEquals(1, getTotalTimeFromReports(reports));
    }

    @Test
    void testCase13() {
        List<Report> reports = List.of(
                new Report(1L, false, false, LocalTime.of(1, 0)),
                new Report(2L, false, false, LocalTime.of(2, 0)),
                new Report(3L, false, false, LocalTime.of(3, 0)),
                new Report(4L, false, false, LocalTime.of(4, 0)),
                new Report(5L, true, true, LocalTime.of(5, 0)),
                new Report(6L, true, true, LocalTime.of(6, 0))
        );

        Assert.assertEquals(1, getTotalTimeFromReports(reports));
    }


    private long getTotalTimeFromReports(List<Report> reports) {
        ReportProcessor reportProcessor = new LightStateOnProcessor();

        for (Report report : reports) {
            reportProcessor.processReport(report, TimeTypeEnum.HOUR);
        }

        return reportProcessor.getTotalTime(TimeTypeEnum.HOUR);
    }
}
