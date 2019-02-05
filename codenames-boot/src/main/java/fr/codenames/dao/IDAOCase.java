package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Case;

public interface IDAOCase extends JpaRepository<Case, Integer> {
}