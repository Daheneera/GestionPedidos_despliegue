package es.mdef.gestionpedidos.REST.preguntas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.REST.Familia.FamiliaController;
import es.mdef.gestionpedidos.REST.usuario.UsuarioController;
import es.mdef.gestionpedidos.REST.usuario.UsuarioModel;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;

@Component
public class PreguntaListaAssembler  implements RepresentationModelAssembler<Pregunta, PreguntaListaModel> {

	@Override
	public PreguntaListaModel toModel(Pregunta entity) {
		PreguntaListaModel model = new PreguntaListaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(
		linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel(),
		linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"),
		linkTo(methodOn(FamiliaController.class).one(entity.getFamilia().getId())).withRel("familias"));
				
		
		return model;
	}
	
	public CollectionModel<PreguntaListaModel> toCollection(List<Pregunta> lista) {
		CollectionModel<PreguntaListaModel>  coleccion = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList()));
		coleccion.add(
				linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas")
				);
		
		return  coleccion;
	}

}
