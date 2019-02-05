package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	public Utilisateur findByUsername(String username);
}