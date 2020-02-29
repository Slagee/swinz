package rooms;

import exceptions.RoomNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class RoomController {

    private final RoomRepository repository;
    private final RoomResourceAssembler assembler;

    RoomController(RoomRepository repository, RoomResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/rooms")
    CollectionModel<EntityModel<Room>> all() {
        List<EntityModel<Room>> rooms = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(rooms,
                linkTo(methodOn(RoomController.class).all()).withSelfRel());
    }

    @PostMapping("/rooms")
    ResponseEntity<?> newRoom(@RequestBody Room newRoom) throws URISyntaxException {

        EntityModel<Room> resource = assembler.toModel(repository.save(newRoom));

        return ResponseEntity
                .created(linkTo(methodOn(RoomController.class).one(Objects.requireNonNull(resource.getContent()).getId())).toUri())
                .body(resource);
    }

    @GetMapping("/rooms/{id}")
    EntityModel<Room> one(@PathVariable Long id) {

        Room room = repository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));

        return assembler.toModel(room);
    }

    @PutMapping("/rooms/{id}")
    ResponseEntity<?> replaceRoom(@RequestBody Room newRoom, @PathVariable Long id) throws URISyntaxException {

        Room updatedRoom = repository.findById(id)
                .map(room -> {
                    room.setName(newRoom.getName());
                    return repository.save(room);
                })
                .orElseGet(() -> {
                    newRoom.setId(id);
                    return repository.save(newRoom);
                });

        EntityModel<Room> model = assembler.toModel(updatedRoom);

        return ResponseEntity
                .created(linkTo(methodOn(RoomController.class).one(Objects.requireNonNull(model.getContent()).getId())).toUri())
                .body(model);
    }

    @DeleteMapping("/rooms/{id}")
    void deleteRoom(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
