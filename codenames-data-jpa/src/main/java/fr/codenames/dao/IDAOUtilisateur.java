package fr.codenames.dao;

import javax.security.auth.login.AccountLockedException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.codenames.model.Carte;
import fr.codenames.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Carte, Integer> {
	/**
	 * Méthode d'authentification pour un utilisateur (Joueur ou Administrateur)
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameOrPasswordNotFoundException
	 * @throws AccountLockedException
	 */
	@Query("select u from Utilisateur u where u.username = :username and u.password = :password")
	public Utilisateur auth(@Param("username") String username, @Param("password") String password);
}