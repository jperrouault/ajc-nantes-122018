package fr.formation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOClient;


@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private IDAOClient daoClient;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UtilisateurPrincipal(daoClient.findByEmail(username));
	}
}
