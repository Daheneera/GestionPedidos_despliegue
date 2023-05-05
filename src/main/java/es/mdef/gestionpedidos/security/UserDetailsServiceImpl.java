package es.mdef.gestionpedidos.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.mdef.gestionpedidos.repositorios.usuarioRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private usuarioRepositorio repositorio;
	
	public UserDetailsServiceImpl(usuarioRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Usuario " + username + " no encontrado"));
	}

}