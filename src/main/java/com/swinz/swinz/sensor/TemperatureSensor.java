package com.swinz.swinz.sensor;

import com.swinz.swinz.model.Room;
import com.swinz.swinz.utils.Utils;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.swinz.swinz.constants.Constants.TEMPERATURE_THRESHOLD;

public class TemperatureSensor {
    private static final int MINIMAL_TEMPERATURE = 15;
    private static final int MAXIMAL_TEMPERATURE = 26;

    public static double generateTemperature(Room room) {
        if (!(room.getRadiatorState() || Utils.compareWithThreshold(room.getCurrentTemperature(), room.getSelectedTemperature(), TEMPERATURE_THRESHOLD))) {
            double randomValue = (Math.random() * ((MAXIMAL_TEMPERATURE - MINIMAL_TEMPERATURE) + 1)) + MINIMAL_TEMPERATURE;
            return BigDecimal.valueOf(randomValue).round(new MathContext(4)).doubleValue();
        } else return room.getCurrentTemperature();
    }

}
