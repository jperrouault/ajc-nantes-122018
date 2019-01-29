package fr.formation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.model.Client;

public class UtilisateurPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Client client;

	
	public UtilisateurPrincipal(Client client) {
		if (client == null) {
			throw new UsernameNotFoundException("Le client n'existe pas.");
		}

		this.client = client;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> myAuthorities = new ArrayList<GrantedAuthority>();
		
		if (this.client.getId() == 1) { //C'EST L'ADMIN
			myAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		else { //C'EST PAS L'ADMIN
			myAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return myAuthorities;
	}

	public String getPassword() {
		return this.client.getPassword();
	}

	public String getUsername() {
		return this.client.getEmail();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}