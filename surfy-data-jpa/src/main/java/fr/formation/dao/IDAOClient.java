package fr.formation.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.model.Client;

public interface IDAOClient extends JpaRepository<Client, Integer> {
	@Query("select c from Client c left join fetch c.commandes cmd left join fetch cmd.produitsAchetes pa where c.id = :id")
	public Client findByIdWithProduitsAchetes(@Param("id") int id);
}