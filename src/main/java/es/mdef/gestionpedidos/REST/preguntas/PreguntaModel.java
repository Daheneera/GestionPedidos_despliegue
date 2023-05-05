package es.mdef.gestionpedidos.REST.preguntas;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.REST.usuario.UsuarioModel;
import es.mdef.gestionpedidos.entidades.Usuario;

@Relation(itemRelation="preguntas")
public class PreguntaModel extends RepresentationModel<PreguntaModel> {

	private String enunciado;


	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	

	@Override
	public String toString() {
		return "PreguntaModel [enunciado=" + enunciado +  "]";
	}

	
	
	
	
}
