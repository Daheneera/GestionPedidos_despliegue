package es.mdef.gestionpedidos.REST.preguntas;

import org.springframework.hateoas.RepresentationModel;

import es.mdef.gestionpedidos.entidades.FamiliaImpl;
import es.mdef.gestionpedidos.entidades.Usuario;

public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel> {

		private String enunciado;
		private Usuario usuario;
		private FamiliaImpl familia;
		
		public String getEnunciado() {
			return enunciado;
		}
		public void setEnunciado(String enunciado) {
			this.enunciado = enunciado;
		}
					
		public FamiliaImpl getFamilia() {
			return familia;
		}
		public void setFamilia(FamiliaImpl familia) {
			this.familia = familia;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		@Override
		public String toString() {
			return "PreguntaPostModel [enunciado=" + enunciado + ", usuario=" + usuario + ", familia=" + familia + "]";
		}
	
		
		
		
	
	
	
	
}
