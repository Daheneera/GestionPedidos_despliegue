package es.mdef.gestionpedidos.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="FAMILIAS")
public class FamiliaImpl extends es.mdef.support.Familia{
	

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(unique=true, name = "enumnciado")
		private String enunciado;
		private Long tamano;
		@OneToMany(mappedBy = "familia")
		List<Pregunta> pregunta;

		public Long getTamano() {
			return tamano;
		}

		public void setTamano(Long tamano) {
			this.tamano = tamano;
		}

		
		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public List<Pregunta> getPregunta() {
			return pregunta;
		}

		public void setPregunta(List<Pregunta> pregunta) {
			this.pregunta = pregunta;
		}
		
		

		public String getEnunciado() {
			return enunciado;
		}

		public void setEnunciado(String enunciado) {
			this.enunciado = enunciado;
		}

		@Override
		public String toString() {
			return "Familia [id=" + id + ", enunciado=" + enunciado + ", tamano=" + tamano + "]";
		}

		

		
		
	
	
}
