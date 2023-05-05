package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NoAdministrador extends Usuario {
		public static enum Departamento{
			EMIES, CCESP
		}
		public static enum Tipo{
			Alumno, Docente, Administración
		}
		private Departamento departamento;
		private Tipo tipo;
		public Departamento getDepartamento() {
			return departamento;
		}
		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}
		public Tipo getTipo() {
			return tipo;
		}
		public void setTipo(Tipo tipo) {
			this.tipo = tipo;
		}
		@Override
		public String toString() {
			return "NoAdministrador [departamento=" + departamento + ", tipo=" + tipo + ", getNombre()=" + getNombre()
					+ ", getNombreUsuario()=" + getUsername() + "]";
		}
		
		
		
		
}
