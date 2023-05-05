package es.mdef.gestionpedidos.REST.preguntas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.REST.Familia.FamiliaController;
import es.mdef.gestionpedidos.REST.usuario.UsuarioController;
import es.mdef.gestionpedidos.REST.usuario.UsuarioModel;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;

@Component
public class PreguntaPostAssembler  implements RepresentationModelAssembler<Pregunta, PreguntaPostModel> {

	@Override
	public PreguntaPostModel toModel(Pregunta entity) {
		PreguntaPostModel model = new PreguntaPostModel();
		model.setEnunciado(entity.getEnunciado());
		
		model.add(
				
				
				linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"),
				linkTo(methodOn(FamiliaController.class).one(entity.getFamilia().getId())).withRel("familia"));
				
				
		
		return model;
	}
	
	public Pregunta toEntity(PreguntaPostModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
		pregunta.setUsuario(model.getUsuario());
		pregunta.setFamilia(model.getFamilia());
		
		
		return  pregunta;
	}

}
