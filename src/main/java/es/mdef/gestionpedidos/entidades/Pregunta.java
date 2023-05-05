package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PREGUNTAS")
public class Pregunta extends es.mdef.support.Familia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String enunciado;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UsuarioId")
	private Usuario usuario;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FamiliaId")
	private FamiliaImpl familia;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public FamiliaImpl getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaImpl familia) {
		this.familia = familia;
	}

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", enunciado=" + enunciado +", usuario=" + usuario +", familia=" + familia+"]";
	}

	
	
	
	
}
