package es.mdef.gestionpedidos.REST.Familia;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.REST.preguntas.PreguntaController;
import es.mdef.gestionpedidos.REST.preguntas.PreguntaModel;
import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.Pregunta;

@Component
public class FamiliaPostAssembler implements RepresentationModelAssembler<FamiliaImpl, FamiliaPostModel>{

		@Override
		public FamiliaPostModel toModel(FamiliaImpl entity) {
			FamiliaPostModel model = new FamiliaPostModel();
			model.setEnunciado(entity.getEnunciado());
			WebMvcLinkBuilder selfLink = 
					linkTo(methodOn(FamiliaController.class).one(entity.getId()));
			model.add(
					selfLink.withSelfRel(),
					selfLink.slash("usuarios").withRel("usuarios"),
					linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
			
			return model;
		}
		
		public FamiliaImpl toEntity(FamiliaPostModel model) {
			FamiliaImpl familia = new FamiliaImpl();
			familia.setEnunciado(model.getEnunciado());
			
			
			return  familia;
		}
}
