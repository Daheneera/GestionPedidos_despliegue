package es.mdef.gestionpedidos.REST.Familia;

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

import es.mdef.gestionpedidos.entidades.FamiliaImpl;

import es.mdef.gestionpedidos.repositorios.FamiliaRepositorio;


@RestController
@RequestMapping("/familias")
public class FamiliaController {
	private final FamiliaRepositorio repositorio;
	private final FamiliaAssembler assembler;
	private final FamiliaPostAssembler postAssembler;
	private final FamiliaListaAssembler listaAssembler;
	private final Logger log;
	
	public FamiliaController(FamiliaRepositorio repositorio, FamiliaAssembler assembler,
			 FamiliaPostAssembler postAssembler, FamiliaListaAssembler listaAssembler) {

				this.repositorio = repositorio;
				this.assembler = assembler;
				this.postAssembler = postAssembler;
				this.listaAssembler= listaAssembler;
				log= GestionpedidosApplication.log;
	}
	
	
	
	@PostMapping
	public FamiliaModel add(@RequestBody FamiliaPostModel model) {
		FamiliaImpl familia = repositorio.save(postAssembler.toEntity(model));
		log.info("Ha pado por aqui el model "+model);
		log.info("Añadido "+ familia);
		
		
		return assembler.toModel(familia);
	}
	
	@GetMapping
	public CollectionModel<FamiliaListaModel> all(){
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@GetMapping("{id}")
	public FamiliaModel one(@PathVariable Long id) {
		FamiliaImpl familia = repositorio.findById(id).
				orElseThrow(()-> new RegisterNotFoundException(id, "familia"));
				log.info("REcuperada "+ familia);
				return assembler.toModel(familia);
	}
	
	
		
	@PutMapping("{id}")
	public FamiliaModel edit(@PathVariable Long id, FamiliaPostModel model) {
		FamiliaImpl familia = repositorio.findById(id).map(f -> {
			f.setEnunciado(model.getEnunciado());
			
			return repositorio.save(f);
			}).orElseThrow(()->new RegisterNotFoundException(id, "familia"));
			
		log.info("Actualizada "+familia);
		return assembler.toModel(familia);
	}
	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id ) {
		log.info("Borrada "+ id);
		repositorio.deleteById(id);
	}
	
}
