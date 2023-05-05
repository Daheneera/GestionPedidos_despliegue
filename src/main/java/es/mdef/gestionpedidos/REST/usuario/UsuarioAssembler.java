package es.mdef.gestionpedidos.REST.usuario;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Usuario;



@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, UsuarioModel> {

	@Override
	public UsuarioModel toModel(Usuario entity) {
		UsuarioModel model = new UsuarioModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setRole(entity.getRole());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public UsuarioModel toModel(Administrador entity) {
		UsuarioModel model = new UsuarioModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setTelefono(entity.getTelefono());
		model.setRole(entity.getRole());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public UsuarioModel toModel(NoAdministrador entity) {
		UsuarioModel model = new UsuarioModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setDepartamento(entity.getDepartamento());
		model.setTipo(entity.getTipo());
		model.setRole(entity.getRole());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public Usuario toEntity(UsuarioModel model) {
		Usuario usuario;
		
		switch(model.getRole()) {
		case Administrador:
					Administrador admin = new Administrador();
					admin.setTelefono(model.getTelefono());
					usuario = admin;
					break;
		case NoAdministrador:
					NoAdministrador user = new NoAdministrador();
					user.setDepartamento(model.getDepartamento());
					user.setTipo(model.getTipo());
					usuario=user;
					break;
		default: 
					usuario = new Usuario();
		}
		usuario.setNombre(model.getNombre());
		usuario.setNombreUsuario(model.getNombreUsuario());
		usuario.setRole(model.getRole());
		return usuario;
	}
}