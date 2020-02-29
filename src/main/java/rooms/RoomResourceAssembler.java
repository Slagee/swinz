package rooms;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RoomResourceAssembler implements RepresentationModelAssembler<Room, EntityModel<Room>> {

    @Override
    public EntityModel<Room> toModel(Room entity) {

        return new EntityModel<>(entity,
                linkTo(methodOn(RoomController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(RoomController.class).all()).withRel("rooms"));
    }
}
