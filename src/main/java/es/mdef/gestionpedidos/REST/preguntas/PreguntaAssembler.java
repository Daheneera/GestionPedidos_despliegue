package es.mdef.gestionpedidos.REST.preguntas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.REST.usuario.UsuarioController;
import es.mdef.gestionpedidos.REST.usuario.UsuarioModel;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;

@Component
public class PreguntaAssembler  implements RepresentationModelAssembler<Pregunta, PreguntaModel> {

	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		model.setEnunciado(entity.getEnunciado());
		
		model.add(
				linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"));
		
		return model;
	}
	
	public Pregunta toEntity(PreguntaModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
		
		
		return  pregunta;
	}

}
