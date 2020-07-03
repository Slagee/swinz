import com.swinz.swinz.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UtilsTest {

    @Test
    void compareWithThresholdTest() {
        double valueA = 0.513;
        double valueB = 0.500;
        Assert.assertTrue(Utils.compareWithThreshold(valueA, valueB, 0.013));
    }

    @Test
    void compareWithThresholdTest2() {
        double valueA = 0.5131;
        double valueB = 0.500;
        Assert.assertFalse(Utils.compareWithThreshold(valueA, valueB, 0.013));
    }

    @Test
    void compareWithThresholdTest3() {
        double valueA = 22.0;
        double valueB = 22.19;
        Assert.assertTrue(Utils.compareWithThreshold(valueA, valueB, 0.19));
    }

    @Test
    void compareWithThresholdTest4() {
        double valueA = 18;
        double valueB = 18;
        Assert.assertTrue(Utils.compareWithThreshold(valueA, valueB, 0.19));
    }

    @Test
    void compareWithThresholdTest5() {
        double valueA = 22.39;
        double valueB = 22.5;
        Assert.assertTrue(Utils.compareWithThreshold(valueA, valueB, 0.19));
    }
}