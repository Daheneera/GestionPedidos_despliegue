package es.mdef.gestionpedidos.REST.preguntas;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.GestionpedidosApplication;
import es.mdef.gestionpedidos.REST.RegisterNotFoundException;
import es.mdef.gestionpedidos.entidades.Administrador;
import es.mdef.gestionpedidos.entidades.NoAdministrador;
import es.mdef.gestionpedidos.entidades.Pregunta;
import es.mdef.gestionpedidos.entidades.Usuario;
import es.mdef.gestionpedidos.entidades.Usuario.Role;
import es.mdef.gestionpedidos.repositorios.PreguntaRepositorio;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

	private final PreguntaRepositorio repositorio;
	private final PreguntaAssembler assembler;
	private final PreguntaPostAssembler postAssembler;
	private final PreguntaListaAssembler listaAssembler;
	private final Logger log;
	public PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler assembler,
								 PreguntaPostAssembler postAssembler, PreguntaListaAssembler listaAssembler) {
		
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.postAssembler = postAssembler;
		this.listaAssembler= listaAssembler;
		log= GestionpedidosApplication.log;
	}
	
	@PostMapping
	public PreguntaModel add(@RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.save(postAssembler.toEntity(model));
		log.info("Añadido "+ pregunta);
		
		
		return assembler.toModel(pregunta);
	}
	
	@GetMapping
	public CollectionModel<PreguntaListaModel> all(){
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		Pregunta pregunta = repositorio.findById(id).
				orElseThrow(()-> new RegisterNotFoundException(id, "pregunta"));
				log.info("REcuperada "+ pregunta);
				return assembler.toModel(pregunta);
	}
	
	
		
	@PutMapping("{id}")
	public PreguntaModel edit(@PathVariable Long id, PreguntaPostModel model) {
		Pregunta pregunta = repositorio.findById(id).map(p -> {
			p.setEnunciado(model.getEnunciado());
			
			return repositorio.save(p);
			}).orElseThrow(()->new RegisterNotFoundException(id, "pregunta"));
			
		log.info("Actualizada "+pregunta);
		return assembler.toModel(pregunta);
	}
	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id ) {
		log.info("Borrada "+ id);
		repositorio.deleteById(id);
	}
	
	
	
}
