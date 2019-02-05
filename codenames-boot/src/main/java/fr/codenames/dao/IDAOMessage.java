package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Message;

public interface IDAOMessage extends JpaRepository<Message, Integer> {
	public List<Message> findAllByPartieIdOrderByIdDesc(int id);
	public List<Message> findAllByPartieGrilleIdOrderByIdDesc(int id);
}