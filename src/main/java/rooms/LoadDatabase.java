package rooms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RoomRepository roomRepository, TemperatureRepository temperatureRepository) {
        return args -> {
            log.info("Preloading " +roomRepository.save(new Room("Obyvak")));
            log.info("Preloading " +roomRepository.save(new Room("Kuchyn")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Obyvak")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Obyvak")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Obyvak")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Kuchyn")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Kuchyn")));
            log.info("Preloading " +temperatureRepository.save(new Temperature("Kuchyn")));
        };
    }
}
