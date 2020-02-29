package rooms;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
public class Temperature {

    private @Id @GeneratedValue Long id;
    private String room;
    private double temperature;
    private LocalDateTime dateTime;

    Temperature() {}

    Temperature(String room) {
        this.room = room;
        Random rnd = new Random();
        double decimal = rnd.nextDouble();
        this.temperature = rnd.nextInt(6) + 18 + (Math.round(decimal * 100.0) / 100.0);
        this.dateTime = LocalDateTime.now();
    }
}
