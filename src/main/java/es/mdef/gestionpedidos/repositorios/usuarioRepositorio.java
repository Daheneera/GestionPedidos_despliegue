package es.mdef.gestionpedidos.repositorios;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.Usuario;



public interface usuarioRepositorio extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUserName(String username);
}
