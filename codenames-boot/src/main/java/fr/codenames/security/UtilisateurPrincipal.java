package fr.codenames.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.codenames.model.Utilisateur;

public class UtilisateurPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	
	
	public UtilisateurPrincipal(Utilisateur utilisateur) {
		if (utilisateur == null) {
			throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
		}
		
		this.utilisateur = utilisateur;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utilisateur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}