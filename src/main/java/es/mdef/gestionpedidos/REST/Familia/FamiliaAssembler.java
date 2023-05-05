package es.mdef.gestionpedidos.REST.Familia;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.REST.preguntas.PreguntaController;

import es.mdef.gestionpedidos.entidades.FamiliaImpl;


@Component
public class FamiliaAssembler implements RepresentationModelAssembler<FamiliaImpl, FamiliaModel>{

	@Override
	public FamiliaModel toModel(FamiliaImpl entity) {
		FamiliaModel model = new FamiliaModel();
		model.setEnunciado(entity.getEnunciado());
		model.setTamano(entity.getTamano());
		WebMvcLinkBuilder selfLink = 
				linkTo(methodOn(FamiliaController.class).one(entity.getId()));
		model.add(
				selfLink.withSelfRel(),
				selfLink.slash("usuarios").withRel("usuarios"),
				linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
		
		return model;
	}
	
	public FamiliaImpl toEntity(FamiliaModel model) {
		FamiliaImpl familia = new FamiliaImpl();
		familia.setEnunciado(model.getEnunciado());
		familia.setTamano(model.getTamano());
		
		
		return  familia;
	}
}
