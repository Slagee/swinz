package rooms;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Room {

    private @Id @GeneratedValue Long id;
    private String name;

    Room() {}

    public Room(String name) {
        this.name = name;
    }
}
