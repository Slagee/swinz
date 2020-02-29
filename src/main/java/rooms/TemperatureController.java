package rooms;

import exceptions.TemperatureNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class TemperatureController {

    private final TemperatureRepository temperatureRepository;
    private final TemperatureResourceAssembler assembler;

    TemperatureController(TemperatureRepository temperatureRepository, TemperatureResourceAssembler assembler) {
        this.temperatureRepository = temperatureRepository;
        this.assembler = assembler;
    }

    @GetMapping("/temperatures")
    CollectionModel<EntityModel<Temperature>> all() {

        List<EntityModel<Temperature>> temperatures = temperatureRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(temperatures,
                linkTo(methodOn(TemperatureController.class).all()).withSelfRel());
    }

    @GetMapping("/temperatures/{id}")
    EntityModel<Temperature> one(@PathVariable Long id) {
        return assembler.toModel(
                temperatureRepository.findById(id)
                .orElseThrow(() -> new TemperatureNotFoundException(id)));
    }

    @PostMapping("/temperatures")
    ResponseEntity<EntityModel<Temperature>> newTemperature(@RequestBody Temperature temperature) {

        Temperature newTemperature = temperatureRepository.save(temperature);

        return ResponseEntity
                .created(linkTo(methodOn(TemperatureController.class).one(newTemperature.getId())).toUri())
                .body(assembler.toModel(newTemperature));
    }
}
