package exceptions;

public class TemperatureNotFoundException extends RuntimeException {
    public TemperatureNotFoundException(Long id) {
        super("Could not find temperature: " +id);
    }
}
