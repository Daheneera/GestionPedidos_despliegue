package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {

	private String telefono;

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Administrador [telefono=" + telefono + ", getNombre()=" + getNombre() + ", getNombreUsuario()="
				+ getUsername() + "]";
	}
	
	
	
}
