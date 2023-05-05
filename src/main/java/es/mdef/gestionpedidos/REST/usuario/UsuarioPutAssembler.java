package es.mdef.gestionpedidos.REST.usuario;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Usuario;




@Component
public class UsuarioPutAssembler implements RepresentationModelAssembler<Usuario, UsuarioPutModel> {

	@Override
	public UsuarioPutModel toModel(Usuario entity) {
		UsuarioPutModel model = new UsuarioPutModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setRole(entity.getRole());
		
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	
	public UsuarioPutModel toModel(Administrador entity) {
		UsuarioPutModel model = new UsuarioPutModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setRole(entity.getRole());
		model.setTelefono(entity.getTelefono());
		
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public UsuarioPutModel toModel(NoAdministrador entity) {
		UsuarioPutModel model = new UsuarioPutModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setRole(entity.getRole());
		model.setDepartamento(entity.getDepartamento());
		model.setTipo(entity.getTipo());
		
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
				
		return model;
	}
	
	
	public Usuario toEntity(UsuarioPutModel model) {
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