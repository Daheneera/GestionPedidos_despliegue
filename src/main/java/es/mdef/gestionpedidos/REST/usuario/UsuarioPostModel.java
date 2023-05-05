package es.mdef.gestionpedidos.REST.usuario;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpedidos.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpedidos.entidades.Usuario.Role;





@Relation(itemRelation="usuario")
public class UsuarioPostModel extends RepresentationModel<UsuarioPostModel> {
	private String nombre;
	private String nombreUsuario ;
	private String password;
	private Role role;
	private Departamento departamento;
	private Tipo tipo;
	private String telefono;
	
	
	

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "UsuarioPostModel [nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", password=" + password
				+ ", role=" + role + ", departamento=" + departamento + ", tipo=" + tipo + ", telefono=" + telefono
				+ "]";
	}
	
	
	
	
	
	
	
}
