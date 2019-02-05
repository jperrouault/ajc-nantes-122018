package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Carte;

public interface IDAOPartie extends JpaRepository<Carte, Integer> {
}