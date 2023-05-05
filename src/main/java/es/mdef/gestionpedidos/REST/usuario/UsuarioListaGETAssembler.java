package es.mdef.gestionpedidos.REST.usuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Usuario;


@Component
public class UsuarioListaGETAssembler implements RepresentationModelAssembler<Usuario, UsuarioListaGETModel> {

	@Override
	public UsuarioListaGETModel toModel(Usuario entity) {
		UsuarioListaGETModel model = new UsuarioListaGETModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setRole(entity.getRole());
		if(entity instanceof Administrador) {
			Administrador admin = (Administrador) entity;
			model.setTelefono(admin.getTelefono());
		}
		if (entity instanceof NoAdministrador) {
			NoAdministrador noAdmin = (NoAdministrador) entity;
			model.setDepartamento(noAdmin.getDepartamento());
			model.setTipo(noAdmin.getTipo());
		}
				
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				
				);
		return model;
	}
	
	public CollectionModel<UsuarioListaGETModel> toCollection(List<Usuario> lista){
		CollectionModel<UsuarioListaGETModel> collection = CollectionModel.of(
								  lista.stream().map(this::toModel).collect(Collectors.toList()));
		collection.add(
				
				linkTo(methodOn(UsuarioController.class).all()).withRel("usuarios")
				);
		return collection;
	}

}
