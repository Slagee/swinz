package rooms;

import org.springframework.data.jpa.repository.JpaRepository;

interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}
