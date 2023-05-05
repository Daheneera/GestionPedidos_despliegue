package es.mdef.gestionpedidos.REST.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import es.mdef.gestionpedidos.GestionpedidosApplication;
import es.mdef.gestionpedidos.REST.RegisterNotFoundException;
import es.mdef.gestionpedidos.REST.Familia.FamiliaController;
import es.mdef.gestionpedidos.REST.Familia.FamiliaListaAssembler;
import es.mdef.gestionpedidos.REST.Familia.FamiliaListaModel;
import es.mdef.gestionpedidos.REST.preguntas.PreguntaListaAssembler;
import es.mdef.gestionpedidos.REST.preguntas.PreguntaListaModel;
import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.entidades.Usuario.Role;
import es.mdef.gestionpedidos.repositorios.PreguntaRepositorio;
import es.mdef.gestionpedidos.repositorios.usuarioRepositorio;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
		
	
		private final usuarioRepositorio repositorio;
		private final UsuarioAssembler assembler;
		private UsuarioPostAssembler postAssembler;	
		private UsuarioListaGETAssembler listaGetAssembler;
		private PreguntaListaAssembler preguntaListaAssembler;
		private FamiliaListaAssembler listaFamiliaAssembler;
		private final Logger log;
		
		UsuarioController(usuarioRepositorio repositorio, UsuarioAssembler assembler, 
				PreguntaRepositorio preguntaRepositorio, UsuarioListaAssembler listaAssembler, 
				UsuarioPutAssembler putAssembler, UsuarioPostAssembler postAssembler, 
				UsuarioListaGETAssembler  listaGetAssembler, PreguntaListaAssembler preguntaListaAssembler,
				FamiliaListaAssembler listaFamiliaAssembler) {
			this.repositorio = repositorio;
			this.assembler = assembler;
			this.postAssembler = postAssembler;
			this.listaGetAssembler = listaGetAssembler;
			this.preguntaListaAssembler = preguntaListaAssembler;
			this.listaFamiliaAssembler = listaFamiliaAssembler;
			log = GestionpedidosApplication.log;
		}
	
	
	@GetMapping("{id}")
	public UsuarioModel one(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
			
	//	log.info("Recuperado " + usuario);
		return assembler.toModel(usuario);
	}
	
	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> preguntas(@PathVariable Long id) {
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"))
				.getPreguntas();
		log.info("La pregunta es: " + preguntas+ "FIN");
//		Link linkP = linkTo(methodOn(UsuarioController.class).one(id)).withSelfRel();
//	    linkP = Link.of(linkP.getHref() + "/preguntas", "preguntas");
//	    Link linkF=linkTo(methodOn(FamiliaController.class).one(id)).withSelfRel();
//	    linkF = Link.of(linkF.getHref() + "/familias", "familias");
	    return CollectionModel.of(
	            preguntas.stream().map(p -> preguntaListaAssembler.toModel(p)).collect(Collectors.toList())
	            /*,linkP, linkF*/);
	}
	
	@GetMapping("{id}/familias")
	public CollectionModel<FamiliaListaModel> familias(@PathVariable Long id){
		log.info("Ha entrado en id-familia");
		List<FamiliaImpl> familias = new ArrayList<FamiliaImpl>();
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"))
				.getPreguntas();
		for(Pregunta p: preguntas ) {
			log.info("la familia es " +p.getFamilia());
			familias.add(p.getFamilia());
		}
		Set<FamiliaImpl> conjuntoFamilias = new HashSet<FamiliaImpl>(familias);
		return CollectionModel.of(
				conjuntoFamilias.stream().map(f ->listaFamiliaAssembler.toModel(f)).collect(Collectors.toList())
						);
	}

	
	
	@GetMapping
	public CollectionModel<UsuarioListaGETModel> all(){
		return listaGetAssembler.toCollection(repositorio.findAll());
	}
	
	
	@PostMapping
	public UsuarioModel add(@Valid @RequestBody UsuarioPostModel model) {
		
		Usuario usuario;
		
		switch(model.getRole()) {
		case Administrador:
						Administrador admin = new Administrador();
						
						
						admin = (Administrador) postAssembler.toEntity(model);
						admin.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
						repositorio.save(admin);
						
						log.info("Añadido " + admin);
						return assembler.toModel(admin);
						
		case NoAdministrador:
			NoAdministrador user = new NoAdministrador();
			user = (NoAdministrador)postAssembler.toEntity(model);
			user.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
			
			repositorio.save(user);
			
			log.info("Añadido " + user);
			return assembler.toModel(user);
			
		
		default: 
			usuario= new Usuario();
			return assembler.toModel(usuario);
		}
	}
	
	@PutMapping("{id}")
	public UsuarioModel edit(@Valid @PathVariable Long id, @RequestBody UsuarioPutModel model) {
		Usuario usuario = repositorio.findById(id).map(u -> {
			u.setNombre(model.getNombre());
			u.setNombreUsuario(model.getNombreUsuario());
			u.setRole(model.getRole());
			u.setAccountNonExpired(u.isAccountNonExpired());
			u.setAccountNonLocked(u.isAccountNonLocked());
			u.setCredentialsNonExpired(u.isCredentialsNonExpired());
			u.setEnabled(u.isEnabled());
			
			if(u.getRole()==Role.Administrador) {
				((Administrador) u).setTelefono(model.getTelefono());
			}else if (u.getRole()==Role.NoAdministrador) {
				((NoAdministrador) u).setDepartamento(model.getDepartamento());
				((NoAdministrador) u).setTipo(model.getTipo());
			}
			return repositorio.save(u);
			}).orElseThrow(()->new RegisterNotFoundException(id, "usuario"));
			
		log.info("Actualizado "+usuario);
		
		if(usuario.getRole()==Role.Administrador) {
		return assembler.toModel((Administrador)usuario);
		}else if(usuario.getRole()==Role.NoAdministrador) {
			return assembler.toModel((NoAdministrador)usuario);
		}else return assembler.toModel(usuario);
	}
	
	
//	@PutMapping("{id}/password")
//	public void updatePassword(@PathVariable Long id, @RequestParam String password) {
//	Usuario usuario = repositorio.findById(id)
//	.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
//	usuario.setPassword(password); // actualiza el password
//	usuario = repositorio.save(usuario);
//	
//	}
	
	@PatchMapping
	public UsuarioModel edit(@PathVariable Long id, @RequestBody String newPassword) {
		Usuario usuario = repositorio.findById(id).map(u ->{
								u.setPassword(new BCryptPasswordEncoder().encode(newPassword));
								return repositorio.save(u);
								}).orElseThrow(()->new RegisterNotFoundException(id, "usuario"));
		log.info("Actualizado "+ usuario);
		repositorio.save(usuario);
		return assembler.toModel(usuario);
	}
	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado pedido " + id);
		repositorio.deleteById(id);
	}

}
