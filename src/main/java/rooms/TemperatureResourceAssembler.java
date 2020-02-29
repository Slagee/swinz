package rooms;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TemperatureResourceAssembler implements RepresentationModelAssembler<Temperature, EntityModel<Temperature>> {

    @Override
    public EntityModel<Temperature> toModel(Temperature entity) {

        return new EntityModel<>(entity,
                linkTo(methodOn(TemperatureController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(TemperatureController.class).all()).withRel("temperatures"));
    }
}
