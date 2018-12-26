package fr.loupgarou.dao;

import fr.loupgarou.exception.AccountLockedException;
import fr.loupgarou.exception.UsernameOrPasswordNotFoundException;
import fr.loupgarou.model.Utilisateur;

public interface IDAOUtilisateur extends IDAO<Utilisateur> {
	/**
	 * Méthode d'authentification pour un utilisateur (Joueur ou Administrateur)
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameOrPasswordNotFoundException
	 * @throws AccountLockedException
	 */
	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException;
}