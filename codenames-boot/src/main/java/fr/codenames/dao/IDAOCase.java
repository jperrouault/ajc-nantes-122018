package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Case;

public interface IDAOCase extends JpaRepository<Case, Integer> {
	public List<Case> findAllByGrilleId(int id);
}