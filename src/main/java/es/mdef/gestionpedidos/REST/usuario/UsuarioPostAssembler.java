package es.mdef.gestionpedidos.REST.usuario;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Usuario;




@Component
public class UsuarioPostAssembler implements RepresentationModelAssembler<Usuario, UsuarioPostModel> {

	@Override
	public UsuarioPostModel toModel(Usuario entity) {
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	public UsuarioPostModel toModel(Administrador entity) {
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.setTelefono(entity.getTelefono());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public UsuarioPostModel toModel(NoAdministrador entity) {
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.setDepartamento(entity.getDepartamento());
		model.setTipo(entity.getTipo());
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public Usuario toEntity(UsuarioPostModel model) {
		Usuario usuario;
		
		switch(model.getRole()){
		case Administrador:
					Administrador admin = new Administrador();
					admin.setTelefono(model.getTelefono());
					usuario=admin;
					break;
		case NoAdministrador:
					NoAdministrador user = new NoAdministrador();
					user.setDepartamento(model.getDepartamento());
					user.setTipo(model.getTipo());
					usuario= user;
					break;
		default: 
					usuario = new Usuario();
		}
		usuario.setNombre(model.getNombre());
		usuario.setNombreUsuario(model.getNombreUsuario());
		usuario.setPassword(model.getPassword());
		usuario.setRole(model.getRole());
		return usuario;
	}
}